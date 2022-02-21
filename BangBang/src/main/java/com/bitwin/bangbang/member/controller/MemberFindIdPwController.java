package com.bitwin.bangbang.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/search")
public class MemberFindIdPwController {
	// Id 찾기
	@GetMapping("/id")
	public String getSearchId() {
		return "member/searchId";
	}
	
//	@PostMapping("/id")
//	public String postSearchId() {
//		
//	}
}
