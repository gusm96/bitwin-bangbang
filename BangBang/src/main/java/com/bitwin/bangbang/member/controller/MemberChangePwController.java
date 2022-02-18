package com.bitwin.bangbang.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/edit/pw")
public class MemberChangePwController {
	
	@GetMapping
	public String getEditPw() {
		return "member/mypage/changepw";
	}
}
