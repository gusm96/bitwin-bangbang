package com.bitwin.bangbang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.exception.ChangePwInvalidException;
import com.bitwin.bangbang.member.service.MemberChangePwService;
import com.bitwin.bangbang.member.service.MemberPwCheckService;

@Controller
@RequestMapping("/mypage/edit/pw")
public class MemberChangePwController {
	
	@Autowired
	private MemberChangePwService changePwService;
	@Autowired
	private MemberPwCheckService pwCheckService;
	@GetMapping
	public String getEditPw() {
		return "member/mypage/changepw";
	}
	
	@PostMapping
	public String changePw(@RequestParam("currentPw") String currentPw, @RequestParam("newPw1") String newPw, HttpSession session) throws ChangePwInvalidException {
		String page = "";
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		String userid = loginInfo.getUserId();
		
		String check = pwCheckService.checkPw(userid, currentPw);
		if(check.equals("Y")) {
			changePwService.changePw(userid, newPw);
			page = "redirect:/mypage/edit";
		}else {
			throw new ChangePwInvalidException("현재 비밀번호가 일치하지 않습니다.");
		}
		
		return page;
	}
	@ExceptionHandler(ChangePwInvalidException.class)
	public String changeFail(ChangePwInvalidException e) {
		return "error/changeFail";
	}
}
