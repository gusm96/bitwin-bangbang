//package com.bitwin.bangbang;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.bitwin.bangbang.exception.LoginInvalidException;
//import com.bitwin.bangbang.member.domain.KakaoInfo;
//import com.bitwin.bangbang.member.domain.MemberLoginRequest;
//import com.bitwin.bangbang.member.domain.NaverInfo;
//import com.bitwin.bangbang.member.service.MemberLoginService;
//
//@Controller
//@RequestMapping("/")
//public class IndexController {
//
//	@GetMapping
//	public String index(Model model) {
//		KakaoInfo kakao = new KakaoInfo();
//		NaverInfo naver = new NaverInfo();
//		model.addAttribute("kakao", kakao);
//		model.addAttribute("naver", naver);
//		return "index";
//	}
//
//	
//}
