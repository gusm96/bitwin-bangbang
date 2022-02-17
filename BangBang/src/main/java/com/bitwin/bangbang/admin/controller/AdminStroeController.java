package com.bitwin.bangbang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/store/management")
public class AdminStroeController {
	@GetMapping
	public String getStore() {
		return "admin/store/management";
	}
}
