package com.bitwin.bangbang.member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwin.bangbang.exception.ChangePwInvalidException;
import com.bitwin.bangbang.exception.LoginInvalidException;
import com.bitwin.bangbang.member.domain.EditMember;
import com.bitwin.bangbang.member.domain.KakaoInfo;
import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.domain.MemberLoginRequest;
import com.bitwin.bangbang.member.domain.MemberRegRequest;
import com.bitwin.bangbang.member.domain.NaverInfo;
import com.bitwin.bangbang.member.domain.SearchPassword;
import com.bitwin.bangbang.member.domain.SimpleRegRequest;
import com.bitwin.bangbang.member.service.MemberLoginService;
import com.bitwin.bangbang.member.service.MemberCheckService;
import com.bitwin.bangbang.member.service.MemberService;
import com.bitwin.bangbang.member.service.SimpleLoginService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private MemberLoginService loginService;

	@Autowired
	private SimpleLoginService apiService;

	@Autowired
	private MemberCheckService checkService;

	// 로그인
	@GetMapping("/login")
	public String getLogin(Model model) {
		KakaoInfo kakao = new KakaoInfo();
		NaverInfo naver = new NaverInfo();
		model.addAttribute("kakao", kakao);
		model.addAttribute("naver", naver);
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

	@GetMapping("/login/oauth/{snsname}")
	public String socialLogin(@PathVariable("snsname") String snsname, @RequestParam("code") String code,
			HttpSession session) {
		String page = "";

		String access_Token = apiService.getAccessToken(snsname, code);
		HashMap<String, Object> userInfo = apiService.getUserInfo(snsname, access_Token);
		// DB에 등록 된 회원인지 확인
		String email = (String) userInfo.get("email");
		int countEmail = apiService.checkEmail(email);

		if (countEmail > 0) {
			// email 로 회원 정보 가져온다.
			// session 에 로그인 정보 등록
			session.setAttribute("loginInfo", apiService.getLoginInfo(email));
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("loginType", snsname);
			page = "redirect:/main/mainpage";
		} else {
			// userInfo 값을 joinform 으로 전달해 회원가입 실행.
			session.setAttribute("userInfo", userInfo);
			page = "redirect:/member/join/simple-reg";
		}

		return page;
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/mainpage";
	}

	// Kakao api 로그아웃
	@GetMapping("/logout/oauth/kakao")
	public String kakaoLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/mainpage";
	}

	// Id 찾기
	@GetMapping("/search/id")
	public String getSearchId() {
		return "member/searchId";
	}

	@PostMapping("/search/id")
	public String postSearchId(@RequestParam("email") String email, Model model) {
		model.addAttribute("result", service.searchById(email));
		return "member/searchIdComplete";
	}

	// PW 찾기
	@GetMapping("/search/pw")
	public String getSearchPw() {
		return "member/searchPw";
	}

	@PostMapping("/search/pw")
	public String postSearchPw(SearchPassword searchPW, Model model) {
		model.addAttribute("result", service.searchByPw(searchPW));
		return "member/searchPwComplete";
	}

	// 일반 회원가입
	@GetMapping("/join/general")
	public String getGeneralMember() {
		return "member/joinform";
	}

	@PostMapping("/join/general")
	public String postGeneralMember(MemberRegRequest regRequest, Model model) {
		System.out.println("regRequest: " + regRequest);

		model.addAttribute("result", service.insertMember(regRequest));
		return "member/regComplete";
	}

	// 중복 체크 기능
	// 이메일 체크
	@GetMapping("join/general/checkemail")
	@ResponseBody
	public String checkEmail(@RequestParam("email") String email) {
		return checkService.checkEmail(email);
	}
	// 아이디 체크
	@GetMapping("/join/general/checkid")
	@ResponseBody
	public String checkId(@RequestParam("userid") String userId) {
		return checkService.checkId(userId);
	}
	// 비밀번호 체크
	@GetMapping("/mypage/pw/checkpw")
	@ResponseBody
	public String checkPw(@RequestParam("currentpw") String currentPw, HttpSession session) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String userid = loginInfo.getUserId();

		return checkService.checkPw(userid, currentPw);
	}

	// 간편 회원가입
	@GetMapping("/join/simple-reg")
	public String getSimpleReg() {
		return "member/simplereg";
	}

	@PostMapping("/join/simple-reg")
	public String postSimpleReg(SimpleRegRequest regRequest, HttpServletRequest req, Model model) {

		model.addAttribute("result", service.insertSimpleMember(regRequest, req));

		return "member/regComplete";
	}

	// 마이페이지 회원정보 수정
	@GetMapping("/mypage")
	public String getMemberEdit(HttpSession session, Model model) {
		// 현재 로그인한 정보를 session 에서 가져온다.
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		int uidx = loginInfo.getUidx();
		model.addAttribute("member", service.getMember(uidx));
		return "member/mypage/editform";
	}
	
	@PostMapping("/mypage")
	public String postMemeberEdit(EditMember editMember, Model model, HttpServletRequest req)
			throws IllegalStateException, IOException {
		model.addAttribute("result", service.editMember(editMember, req));
		return "member/mypage/editComplete";
	}

	// 비밀번호 변경
	@GetMapping("/mypage/pw")
	public String getEditPw() {
		return "member/mypage/changepw";
	}

	@PostMapping("/mypage/pw")
	public String changePw(@RequestParam("currentPw") String currentPw, @RequestParam("newPw1") String newPw,
			HttpSession session, Model model) throws ChangePwInvalidException {
		String page = "";
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String userid = loginInfo.getUserId();

		String check = checkService.checkPw(userid, currentPw);
		if (check.equals("Y")) {
			model.addAttribute("result", service.changePw(userid, newPw));
			session.invalidate();
			page = "member/mypage/changeComplete";
		} else {
			throw new ChangePwInvalidException("현재 비밀번호가 일치하지 않습니다.");
		}

		return page;
	}

	@ExceptionHandler(ChangePwInvalidException.class)
	public String changeFail(ChangePwInvalidException e) {
		return "error/changeFail";
	}
}
