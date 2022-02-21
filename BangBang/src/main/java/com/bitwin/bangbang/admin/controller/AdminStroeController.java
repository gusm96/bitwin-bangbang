package com.bitwin.bangbang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/store/management")
public class AdminStroeController {
	@GetMapping
	public String getStore() {
		return "admin/store/management";
	}
	
	@GetMapping("/reg")
	public String getStoreRegForm() {
		return "admin/store/regform";
	}
	
	@PostMapping("/reg")
	public String postStoreReg(@RequestParam("sname") String sname) {
		
		
		return "redirect:/admin/store/management/"+sname;
	}
}
