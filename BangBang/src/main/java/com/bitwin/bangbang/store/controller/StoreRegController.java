package com.bitwin.bangbang.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/store/reg")
public class StoreRegController {
	
	@GetMapping
	public String getStoreReg() {
		return "store/regform";
	}
	
	
}
