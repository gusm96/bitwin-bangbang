package com.bitwin.bangbang.store.controller;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.member.exception.LoginInvalidException;
import com.bitwin.bangbang.store.domain.StoreLoginRequest;
import com.bitwin.bangbang.store.domain.StoreSearchPassword;
import com.bitwin.bangbang.store.service.StoreService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class StoreLoginController {
	private final StoreService service;

	@GetMapping("/login/store")
	public String getStoreLogin() {
		return "store/loginform";
	}

	@PostMapping("/login/store")
	public String postStoreLogin(StoreLoginRequest loginReq, HttpSession session) throws LoginInvalidException {
		// login service
		return service.storeLogin(loginReq, session);
	}

	@ExceptionHandler(LoginInvalidException.class)
	public String loginFail(LoginInvalidException e) {
		return "error/loginFail";
	}

	// 로그아웃
	@GetMapping("/logout/store")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/mainpage";
	}

	// Id 찾기
	@GetMapping("/login/store/search/id")
	public String getSearchId() {
		return "store/searchId";
	}

	@PostMapping("/login/store/search/id")
	public String postSearchId(@RequestParam("email") String email, Model model) {
		model.addAttribute("result", service.searchById(email));
		return "store/searchIdComplete";
	}

	// PW 찾기
	@GetMapping("/login/store/search/pw")
	public String getSearchPw() {
		return "store/searchPw";
	}

	@PostMapping("/login/store/search/pw")
	public String postSearchPw(StoreSearchPassword searchPW, Model model) {
		model.addAttribute("result", service.searchByPw(searchPW));
		return "store/searchPwComplete";
	}
}
