package com.bitwin.bangbang.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.member.service.MemberEditService;

@Controller
@RequestMapping("/mypage/edit")
public class MemberEditController {
	
	@Autowired
	private MemberEditService editService;
	
	@GetMapping
	public String getMypageEdit(@RequestParam("uidx") int uidx, Model model) {
		
		model.addAttribute("member", editService.getMember(uidx));
		
		return "member/mypage/editform";
	}
}
