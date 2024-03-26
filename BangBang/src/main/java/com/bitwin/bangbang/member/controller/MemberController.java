package com.bitwin.bangbang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.bitwin.bangbang.member.domain.*;
import com.bitwin.bangbang.member.exception.ChangePwInvalidException;
import com.bitwin.bangbang.member.exception.LoginInvalidException;
import com.bitwin.bangbang.member.service.*;
import com.bitwin.bangbang.member.service.implement.MemberLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.bitwin.bangbang.member.domain.Result.*;

@Controller
@RequiredArgsConstructor // 생성자 주입
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MailSender mailSender;
    private final MemberLoginService loginService;
    private final PasswordStrengthCheck pwStrengthCheck;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${naver.redirect_uri}")
    private String naverRedirectUri;

    @Value("${naver.client_id}")
    private String naverClientId;

    // 일반 회원가입
    @GetMapping("/signup")
    public String getSignUpView() {
        return "member/signupForm";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid SignUpReqDto regRequest, Model model) {
        try {
            MemberDto memberDto = memberService.signUp(regRequest);
            mailSender.send(memberDto.getEmail(), memberDto.getUsername());
            model.addAttribute("response", ResultResponse.builder()
                    .result(SIGNUP_SUCCESS)
                    .build());
        } catch (Exception e) {
            model.addAttribute("response", ResultResponse.builder()
                    .result(FAILED.setMessage(e.getMessage()))
                    .build());
        }
        return MemberRouter.SIGNUP_SUCCESS_REDIRECT.getRouter();
    }

    // 중복 체크 기능
    // 이메일 체크
    @GetMapping("/signup/checkemail")
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) {
        return memberService.checkEmail(email);
    }

    // 아이디 체크
    @GetMapping("/signup/checkid")
    @ResponseBody
    public String checkId(@RequestParam("userid") String userId) {
        return memberService.checkId(userId);
    }

    // 비밀번호 체크
    @RequestMapping(method = RequestMethod.GET, value = "/signup/checkpw", produces = "application/text; charset=utf8")
    @ResponseBody
    public String pwStrengthCheck(@RequestParam("password") String password) {
        return pwStrengthCheck.ConfirmPasswordConditions(password).getValue();
    }

    @GetMapping("/mypage/pw/checkpw")
    @ResponseBody
    public String checkPw(@RequestParam("currentpw") String currentPw, HttpSession session) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        String username = loginInfo.getUsername();

        return memberService.checkPw(username, currentPw);
    }

    // 로그인
    @GetMapping("/login")
    public String getLogin(Model model) {
        HashMap<String, String> social = new HashMap<>();
        social.put("kakao_redirect_uri", kakaoRedirectUri);
        social.put("kakao_client_id", kakaoClientId);
        social.put("naver_redirect_uri", naverRedirectUri);
        social.put("naver_client_id", naverClientId);
        model.addAttribute("social", social);
        return "member/loginform";
    }

    @PostMapping("/login")
    public String postLogin(MemberLoginRequest loginRequest, HttpServletResponse res, HttpSession session)
            throws LoginInvalidException {
        return loginService.login(loginRequest, res, session);
    }

    @ExceptionHandler(LoginInvalidException.class)
    public String loginFail(LoginInvalidException e) {
        return "error/loginFail";
    }

    // Kakako / Naver Login API
    // 받아온 코드로 Login 진행
    @GetMapping("/login/oauth/{snsname}")
    public String socialLogin(@PathVariable("snsname") String snsname, @RequestParam("code") String code,
                              HttpSession session) throws LoginInvalidException {
        return loginService.socialLogin(session, snsname, code);
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main/mainpage";
    }

  /*  // Kakao api 로그아웃
    @GetMapping("/logout/oauth/kakao")
    public String kakaoLogout(HttpSession session) {
        loginService.kakaoLogout();
        session.invalidate();
        return "redirect:/main/mainpage";
    }*/

    // Id 찾기
    @GetMapping("/search/id")
    public String getSearchId() {
        return "member/searchId";
    }

    @PostMapping("/search/id")
    public String searchUsername(@RequestParam("email") String email, Model model) {
        model.addAttribute("result", memberService.searchUsernameByEmail(email));
        return "member/searchIdComplete";
    }

    // PW 찾기
    @GetMapping("/search/pw")
    public String getSearchPw() {
        return "member/searchPw";
    }

    @PostMapping("/search/pw")
    public String searchPassword(@RequestBody PasswordResetRequestDto requestDto, Model model) {
        model.addAttribute("result", memberService.issueTemporaryPassword(requestDto));
        return "member/searchPwComplete";
    }


    // 간편 회원가입
    @GetMapping("/simple-signup")
    public String getSimpleReg() {
        return "member/simplereg";
    }

    @PostMapping("/simple-signup")
    public String postSimpleReg(SimpleSignUpReqDto regRequest, HttpServletRequest req, Model model) {

        model.addAttribute("result", memberService.simpleSignUp(regRequest));

        return "signupResult";
    }

    // 마이페이지 회원정보 수정
    @GetMapping("/mypage")
    public String getMemberEdit(HttpSession session, Model model) {
        // 현재 로그인한 정보를 session 에서 가져온다.
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        Long memberId = loginInfo.getMemberId();
        model.addAttribute("member", memberService.retrieveMemberById(memberId));
        return "member/mypage/editform";
    }

 /*   @PostMapping("/mypage")
    public String updateMemberInfo(EditMember editMember, Model model, HttpServletRequest req)
            throws IllegalStateException {
        model.addAttribute("result", memberService.editMember(editMember, req));
        return "member/mypage/editComplete";
    }*/

    // 비밀번호 변경
    @GetMapping("/mypage/pw")
    public String getEditPw() {
        return "member/mypage/changepw";
    }

    @PostMapping("/mypage/pw")
    public String changePw(@RequestParam("currentPw") String currentPw,
                           @RequestParam("newPw1") String newPw,
                           HttpSession session, Model model) {
        String page = "";
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        String username = loginInfo.getUsername();
        try {
            model.addAttribute("result", memberService.changePassword(username, currentPw, newPw));
            session.invalidate();
            page = "member/mypage/changeComplete";
        } catch (Exception e) {

        }
        return page;
    }

    @ExceptionHandler(ChangePwInvalidException.class)
    public String changeFail(ChangePwInvalidException e) {
        return "error/changeFail";
    }
}
