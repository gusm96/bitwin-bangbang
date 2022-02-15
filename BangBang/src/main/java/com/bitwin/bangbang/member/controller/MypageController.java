package com.bitwin.bangbang.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@GetMapping
	public String getMypage() {
		return "member/mypage/mypage";
	}
}
