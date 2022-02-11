package com.bitwin.bangbang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {
	@RequestMapping("/join")
	public String getJoin() {
		return "member/join";
	}
}
