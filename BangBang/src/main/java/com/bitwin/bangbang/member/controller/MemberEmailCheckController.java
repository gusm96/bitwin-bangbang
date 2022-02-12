package com.bitwin.bangbang.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwin.bangbang.member.service.MemberEmailCheckService;

@Controller
public class MemberEmailCheckController {
	@Autowired
	private MemberEmailCheckService checkEmailService;
	
	@RequestMapping("/join/general/checkemail")
	@ResponseBody
	public String checkEmail(
			@RequestParam("email") String email
			) {
		return checkEmailService.checkEmail(email);
	}
}
