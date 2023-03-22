package com.bitwin.bangbang.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitwin.bangbang.member.domain.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.exception.LoginInvalidException;
import com.bitwin.bangbang.member.dao.MemberDao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class MemberLoginService {
	private MemberDao dao;
	@Autowired
	private SqlSessionTemplate template;
	@Autowired
	private BCryptPasswordEncoder encoder;
	public String login(MemberLoginRequest loginRequest, HttpServletResponse res, HttpSession session)
			throws LoginInvalidException {
		String viewName = null;

		Member member = null;

		dao = template.getMapper(MemberDao.class);

		member = dao.selectById(loginRequest.getUserid());

		if (member == null) {
			throw new LoginInvalidException("아이디 또는 비밀번호가 틀립니다.");
		} else if (!encoder.matches(loginRequest.getPassword(), member.getPassword())) {
			// 사용자가 입력한 비밀번호와 Member객체가 가지고 있는 비밀번호를 확인
			throw new LoginInvalidException("아이디 또는 비밀번호가 틀립니다.");
		}
		// session에 로그인 데이터를 저장한다.
		session.setAttribute("loginInfo", member.getLoginInfo());
		session.setAttribute("loginType", "general");
		
		
		// id 저장하기 체크 처리 : checked 상태 -> "on" -> 쿠키 저장
		if (loginRequest.getSaveid() != null && loginRequest.getSaveid().equals("on")) {

			Cookie c = new Cookie("saveId", loginRequest.getUserid());
			res.addCookie(c);
		}else if(loginRequest.getSaveid() == null) {
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
	public ApiToken getAccessToken(String snsname, String authorize_code) {
		ApiToken token = new ApiToken();
		SnsApi api = setApiInfo(snsname);
		try {
			URL url = new URL(api.getTokenURL()); // URL 객체 생성
			// url 의 프로토콜이 https:// 이기 때문에 HttpURLConnection객체로 캐스팅
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + api.getClient_id());
			sb.append("&client_secret=" + api.getClient_secret());
			sb.append("&redirect_uri=" + api.getRedirect_uri());
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush(); // 아직 BufferedWriter를 닫으면 안되기에 flush() 사용

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				System.out.println(line);
				result += line;
			}

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			token.setAccess_Token(element.getAsJsonObject().get("access_token").getAsString());
			token.setRefresh_Token(element.getAsJsonObject().get("refresh_token").getAsString());
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
	public SnsApi setApiInfo(String snsname){
		SnsApi api = null;
		if(snsname.equals("kakao")){
			KakaoInfo kakao = new KakaoInfo();
			api = new SnsApi(kakao.getTokenURL(),
					kakao.getClient_id(),
					kakao.getClient_secret(),
					kakao.getRedirect_uri());
		}else if (snsname.equals("naver")){
			NaverInfo naver = new NaverInfo();
			api = new SnsApi(naver.getTokenURL(),
					naver.getClient_id(),
					naver.getClient_secret(),
					naver.getRedirect_uri());
		}
		return api;
	}
	public HashMap<String, Object> getUserInfo(String snsname, String access_Token) {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		if (snsname.equals("kakao")) {
			userInfo = getKakaoUserInfo(userInfo, access_Token);
		} else if (snsname.equals("naver")) {
			userInfo = getNaverUserInfo(userInfo, access_Token);
		}
		return userInfo;
	}
	public HashMap<String, Object> getKakaoUserInfo(HashMap<String, Object> userInfo, String access_Token) {
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profile = properties.getAsJsonObject().get("profile_image").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			String range = kakao_account.getAsJsonObject().get("age_range").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("profile", profile);
			userInfo.put("email", email);
			userInfo.put("reage", range);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	public HashMap<String, Object> getNaverUserInfo(HashMap<String, Object> userInfo, String access_Token){
		String reqURL = "https://openapi.naver.com/v1/nid/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("response").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("name").getAsString();
			String profile = properties.getAsJsonObject().get("profile_image").getAsString();
			String email = properties.getAsJsonObject().get("email").getAsString();
			String range = properties.getAsJsonObject().get("age").getAsString();
			String phonenum = properties.getAsJsonObject().get("mobile").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("profile", profile);
			userInfo.put("email", email);
			userInfo.put("reage", range);
			userInfo.put("phonenum", phonenum);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	public int checkEmail(String email) {
		int resultCnt = 0;

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.selectCountByEmail(email);

		return resultCnt;

	}
	public LoginInfo getLoginInfo(String email) {
		LoginInfo loginInfo = null;

		dao = template.getMapper(MemberDao.class);

		loginInfo = dao.selectByEmail(email);

		return loginInfo;
	}
	public void kakaoLogout(){
		String reqURL ="";
	}
}
