package com.bitwin.bangbang.member.service.implement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwin.bangbang.member.domain.*;
import com.bitwin.bangbang.member.service.MemberLoginService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.exception.LoginInvalidException;
import com.bitwin.bangbang.member.dao.MemberDao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;

import static com.bitwin.bangbang.member.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private MemberDao dao;
    private final NaverInfo naverInfo;
    private final KakaoInfo kakaoInfo;
    private final SqlSessionTemplate template;
    private final BCryptPasswordEncoder encoder;

    @Override
    public String login(MemberLoginRequest loginRequest, HttpServletResponse res, HttpSession session) throws LoginInvalidException {
        String viewName = null;

        dao = template.getMapper(MemberDao.class);

        Member member = dao.findByUsername(loginRequest.getUsername()).orElseThrow(
                () -> new LoginInvalidException(MEMBER_NOT_FOUND.getErrorMessage())
        );
        if (!encoder.matches(loginRequest.getPassword(), member.getPassword())) {
            // 사용자가 입력한 비밀번호와 Member객체가 가지고 있는 비밀번호를 확인
            throw new LoginInvalidException(PASSWORD_MISMATCH.getErrorMessage());
        }
        // session에 로그인 데이터를 저장한다.
        session.setAttribute("loginInfo", member.getLoginInfo());
        session.setAttribute("loginType", "general");

        // id 저장하기 체크 처리 : checked 상태 -> "on" -> 쿠키 저장
        if (loginRequest.getSaveId() != null && loginRequest.getSaveId().equals("on")) {
            Cookie c = new Cookie("saveId", loginRequest.getUsername());
            res.addCookie(c);
        } else if (loginRequest.getSaveId() == null) {
            // check 상태 -> "null" 쿠키 null
            Cookie c = new Cookie("saveId", null);
            res.addCookie(c);
        }

        if (loginRequest.getUrl().length() > 0) {
            viewName = "redirect:" + loginRequest.getUrl();
        } else {
            viewName = "redirect:/main/mainpage";
        }
        return viewName;
    }

    @Override
    public String socialLogin(HttpSession session, String snsName, String code) {
        // AccessToken 메서드 실행
        ApiToken token = getAccessToken(snsName, code);
        SimpleMember simpleMember = getUserInfo(snsName, token.getAccess_Token());
        // DB에 등록 된 회원인지 확인
        String email = simpleMember.getEmail();

        int countEmail = checkEmail(email);

        if (countEmail > 0) {
            // email 로 회원 정보 가져온다.
            // session 에 로그인 정보 등록
            session.setAttribute("loginInfo", getLoginInfo(email));
            session.setAttribute("access_Token", token.getAccess_Token());
            session.setAttribute("loginType", snsName);
            return "redirect:/main/mainpage";
        } else {
            // userInfo 값을 joinform 으로 전달해 회원가입 실행.
            session.setAttribute("userInfo", simpleMember);
            return "redirect:/member/join/simple-reg";
        }
    }

    private ApiToken getAccessToken(String snsName, String authorize_code) {
        SnsApi api = setApiInfo(snsName);
        try {
            URL url = new URL(api.getTokenURL()); // URL 객체 생성
            // url 의 프로토콜이 https:// 이기 때문에 HttpURLConnection객체로 캐스팅
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            AuthorizationRequest request = AuthorizationRequest.builder()
                    .grantType("authorization_code")
                    .clientId(api.getClient_id())
                    .clientSecret(api.getClient_secret())
                    .redirectUri(api.getRedirect_uri())
                    .code(authorize_code)
                    .build();
            bw.write(request.toString());
            bw.flush(); // 아직 BufferedWriter를 닫으면 안되기에 flush() 사용

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            br.close();
            bw.close();

            return ApiToken.builder()
                    .access_Token(element.getAsJsonObject().get("access_token").getAsString())
                    .refresh_Token(element.getAsJsonObject().get("refresh_token").getAsString())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SnsApi setApiInfo(String snsName) {
        if (snsName.equals("kakao")) {
            return SnsApi.builder()
                    .tokenURL(kakaoInfo.getTokenUrl())
                    .client_id(kakaoInfo.getClientId())
                    .client_secret(kakaoInfo.getClientSecret())
                    .redirect_uri(kakaoInfo.getRedirectUri())
                    .build();
        } else if (snsName.equals("naver")) {
            return SnsApi.builder()
                    .tokenURL(naverInfo.getTokenUrl())
                    .client_id(naverInfo.getClientId())
                    .client_secret(naverInfo.getClientSecret())
                    .redirect_uri(naverInfo.getRedirectUri())
                    .build();
        } else {
            return null;
        }
    }

    private SimpleMember getUserInfo(String snsName, String access_Token) {
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        if (snsName.equals("kakao")) {
            return getKakaoUserInfo(access_Token);
        } else if (snsName.equals("naver")) {
            return getNaverUserInfo(access_Token);
        } else {
            return null;
        }
    }

    private SimpleMember getKakaoUserInfo(String accessToken) {
        try {
            URL url = new URL(kakaoInfo.getUserInfoRequestUri());
            String response = sendHttpRequest(url, accessToken);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String profile = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            String range = kakaoAccount.getAsJsonObject().get("age_range").getAsString();

            return SimpleMember.builder()
                    .nickname(nickname)
                    .profile(profile)
                    .email(email)
                    .range(range)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SimpleMember getNaverUserInfo(String accessToken) {
        try {
            URL url = new URL(naverInfo.getUserInfoRequestUri());
            String response = sendHttpRequest(url, accessToken);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response);

            JsonObject properties = element.getAsJsonObject().get("response").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("name").getAsString();
            String profile = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = properties.getAsJsonObject().get("email").getAsString();
            String range = properties.getAsJsonObject().get("age").getAsString();
            String phoneNum = properties.getAsJsonObject().get("mobile").getAsString();

            return SimpleMember.builder()
                    .nickname(nickname)
                    .profile(profile)
                    .email(email)
                    .range(range)
                    .phoneNum(phoneNum)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String sendHttpRequest(URL url, String accessToken) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int checkEmail(String email) {
        int resultCnt = 0;
        dao = template.getMapper(MemberDao.class);
        resultCnt = dao.countByEmail(email);
        return resultCnt;
    }

    private LoginInfo getLoginInfo(String email) {
        dao = template.getMapper(MemberDao.class);
        return dao.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(MEMBER_NOT_FOUND.getErrorMessage())
        );
    }
}
