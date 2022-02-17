package com.bitwin.bangbang.member.controller;

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
import com.bitwin.bangbang.member.exception.LoginInvalidException;
import com.bitwin.bangbang.member.service.MemberLoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private MemberLoginService loginService;
	
	@GetMapping
	public String getLogin(KakaoInfo kakaoinfo, Model model) {
		model.addAttribute("kakao",kakaoinfo);
		return "member/loginform";
	}

	@PostMapping
	public String postLogin(MemberLoginRequest loginRequest,HttpServletResponse res, HttpSession session) throws LoginInvalidException {
		
		return loginService.login(loginRequest, res, session);
	}
	@ExceptionHandler(LoginInvalidException.class)
	public String loginFail(LoginInvalidException e) {
		return "error/loginFail";
	}

}
