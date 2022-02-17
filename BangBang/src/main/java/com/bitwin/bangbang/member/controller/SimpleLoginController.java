package com.bitwin.bangbang.member.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.member.service.SimpleLoginService;

@Controller
@RequestMapping("/login/oauth")
public class SimpleLoginController {

	@Autowired
	private SimpleLoginService apiService;

	@GetMapping("{snsname}")
	public String kakaoLogin(@PathVariable("snsname") String snsname, @RequestParam("code") String code,
			HttpSession session) {
		String page = "";

		String access_Token = apiService.getAccessToken(snsname, code);
		HashMap<String, Object> userInfo = apiService.getUserInfo(snsname, access_Token);
		// DB에 등록 된 회원인지 확인
		String email = (String) userInfo.get("email");
		int countEmail = apiService.checkEmail(email);

		if (countEmail > 0) {
			// email 로 회원 정보 가져온다.
			// session 에 로그인 정보 등록
			session.setAttribute("loginInfo", apiService.getLoginInfo(email));
			System.out.println("loginInfo: " + apiService.getLoginInfo(email));
			session.setAttribute("access_Token", access_Token);
			session.setAttribute("loginType", "sns");
			page = "redirect:/";
		} else {
			// userInfo 값을 joinform 으로 전달해 회원가입 실행.
			session.setAttribute("userInfo", userInfo);
			page = "redirect:/join/simple-reg";
		}

		return page;
	}

}
