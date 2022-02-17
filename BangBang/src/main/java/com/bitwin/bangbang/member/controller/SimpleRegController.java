package com.bitwin.bangbang.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.member.domain.SimpleRegRequest;
import com.bitwin.bangbang.member.service.SimpleRegService;

@Controller
@RequestMapping("/join/simple-reg")
public class SimpleRegController {
	
	@Autowired
	private SimpleRegService regService;
	
	@GetMapping
	public String getSimpleReg() {
		return "member/simplereg";
	}
	@PostMapping
	public String postSimpleReg(SimpleRegRequest regRequest, HttpServletRequest req , Model model) {
		
		model.addAttribute("result", regService.insertSimpleMember(regRequest, req));
		
		return "redirect:/login";
	}
}
