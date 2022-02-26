package com.bitwin.bangbang.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.exception.LoginInvalidException;
import com.bitwin.bangbang.store.domain.StoreLoginInfo;
import com.bitwin.bangbang.store.domain.StoreLoginRequest;
import com.bitwin.bangbang.store.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	@Autowired
	private StoreService service;
	
	@GetMapping
	public String getStore() {
		return "store/store";
	}
	
	@GetMapping("/mypage")
	public String getStoreMypage(HttpSession session, Model model) {
		model.addAttribute("store", service.getStoreInfo(session));
		return "store/mypage";
	}
	
	@GetMapping("/mypage/edit")
	public String storeEditRequest (HttpSession session, Model model) {
		model.addAttribute("store", service.getStoreInfo(session));
		return "store/editform";
	}
	@PostMapping
	@GetMapping("/fee")
	public String getStoreFee () {
		return "store/fee";
	}
}