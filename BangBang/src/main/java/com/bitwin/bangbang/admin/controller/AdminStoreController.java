package com.bitwin.bangbang.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.admin.service.AdminStoreService;

@Controller
@RequestMapping("/admin/store")
public class AdminStoreController {
	@Autowired
	private AdminStoreService storeService;

	@GetMapping
	public String getStore(Model model) {
		model.addAttribute("store", storeService.selectAll());
		return "admin/store/management";
	}

	@GetMapping("/reg")
	public String getStoreRegForm() {
		return "admin/store/regform";
	}

	@PostMapping("/reg")
	public String postStoreReg(@RequestParam("sname") String sname) {
		
		return "redirect:/admin/store/management/" + sname;
	}
}
