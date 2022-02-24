package com.bitwin.bangbang.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.store.dao.StoreLoginInfo;
import com.bitwin.bangbang.store.service.StoreService;

@Controller
public class StoreController {
	@Autowired
	private StoreService service;
	
	@GetMapping("/login/store")
	public String getStoreLogin() {
		return "store/loginform";
	}
	@PostMapping("/login/store")
	public String postStoreLogin(StoreLoginInfo loginInfo, HttpSession session, Model model) {
		// login service
		service.getStoreInfo(loginInfo);
		return "store/login";
	}
	
	@RequestMapping("/store")
	@GetMapping
	public String getStore() {
		return "store/store";
	}
	
	@GetMapping("/mypage")
	public String getStoreMypage(HttpSession session, Model model) {
		return "store/mypage";
	}
}
