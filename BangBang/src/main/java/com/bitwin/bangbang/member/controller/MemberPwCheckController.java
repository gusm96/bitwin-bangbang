package com.bitwin.bangbang.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.service.MemberPwCheckService;

@Controller
public class MemberPwCheckController {
	
	@Autowired
	private MemberPwCheckService pwCheckService;
	
	@RequestMapping("/mypage/edit/pw/checkpw")
	@ResponseBody
	public String checkPw(@RequestParam("currentpw") String currentPw, HttpSession session) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String userid = loginInfo.getUserId();
		
		return pwCheckService.checkPw(userid, currentPw);
	}
	
}
