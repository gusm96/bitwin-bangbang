package com.bitwin.bangbang.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitwin.bangbang.admin.domain.Admin;
import com.bitwin.bangbang.admin.service.AdminLoginService;
import com.bitwin.bangbang.member.exception.LoginInvalidException;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {
	private final AdminLoginService loginService;
	@RequestMapping(value = "/login/admin" ,method = RequestMethod.GET)
	public String getLogin() {
		return "admin/login";
	}
	@RequestMapping(value = "/login/admin" ,method = RequestMethod.POST)
	public String postLogin(Admin loginReq, HttpServletResponse res, HttpSession session) throws LoginInvalidException {
		return loginService.login(loginReq ,res, session);
	}
	@RequestMapping(value = "/logout/admin" ,method = RequestMethod.GET)
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/mainpage";
	}
}
