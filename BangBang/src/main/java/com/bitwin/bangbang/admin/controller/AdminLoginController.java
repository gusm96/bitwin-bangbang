package com.bitwin.bangbang.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.admin.domain.Admin;
import com.bitwin.bangbang.admin.service.AdminLoginService;
import com.bitwin.bangbang.member.exception.LoginInvalidException;

@Controller
@RequestMapping("/login/admin")
public class AdminLoginController {
	
	@Autowired
	private AdminLoginService loginService;
	
	@GetMapping
	public String getLogin() {
		return "admin/login";
	}
	@PostMapping
	public String postLogin(Admin loginReq, HttpServletResponse res, HttpSession session) throws LoginInvalidException {
		return loginService.login(loginReq ,res, session);
	}
}
