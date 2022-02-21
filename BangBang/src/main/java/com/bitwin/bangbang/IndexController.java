package com.bitwin.bangbang;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.member.domain.KakaoInfo;
import com.bitwin.bangbang.member.domain.MemberLoginRequest;
import com.bitwin.bangbang.member.domain.NaverInfo;
import com.bitwin.bangbang.member.exception.LoginInvalidException;
import com.bitwin.bangbang.member.service.MemberLoginService;

@Controller
@RequestMapping("/*")
public class IndexController {
	@Autowired
	private MemberLoginService loginService;

	@GetMapping
	public String index(Model model) {
		KakaoInfo kakao = new KakaoInfo();
		NaverInfo naver = new NaverInfo();
		model.addAttribute("kakao", kakao);
		model.addAttribute("naver", naver);
		return "index";
	}

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

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// Kakao api 로그아웃
	@GetMapping("/logout/oauth/kakao")
	public String kakaoLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 회원가입
	@GetMapping("/join")
	public String getJoin() {
		return "member/join";
	}

}
