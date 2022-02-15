package com.bitwin.bangbang.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.member.domain.MemberRegRequest;
import com.bitwin.bangbang.member.service.MemberRegService;

@Controller
@RequestMapping("/join/general")
public class MemberRegController {
	@Autowired
	private MemberRegService regService;
	@GetMapping
	public String getGeneralMember() {
		return "member/joinform";
	}
	@PostMapping
	public String postGeneralMember(MemberRegRequest regRequest, HttpServletRequest req ,Model model) {
		model.addAttribute("result", regService.insertMember(regRequest, req));
		return "redirect:/";
	}
}
