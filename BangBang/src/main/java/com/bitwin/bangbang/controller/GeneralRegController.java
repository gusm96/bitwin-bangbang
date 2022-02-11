package com.bitwin.bangbang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.domain.GeneralRegRequest;
import com.bitwin.bangbang.service.GeneralRegService;

@Controller
@RequestMapping("/join/general")
public class GeneralRegController {
	@Autowired
	private GeneralRegService regService;
	@GetMapping
	public String getGeneralMember() {
		return "member/joinform";
	}
	@PostMapping
	public void postGeneralMember(GeneralRegRequest regRequest, HttpServletRequest req ,Model model) {
	//	model.add
	}
}
