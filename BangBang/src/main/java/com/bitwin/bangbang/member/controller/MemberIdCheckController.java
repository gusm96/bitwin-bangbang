package com.bitwin.bangbang.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitwin.bangbang.member.service.MemberIdCheckService;

@Controller
public class MemberIdCheckController {
	@Autowired
	private MemberIdCheckService checkIdService;
	
	@RequestMapping("/join/general/checkid")
	@ResponseBody
	public String checkId(
			@RequestParam("userid") String userId
			) {
		return checkIdService.checkId(userId);
	}
}
