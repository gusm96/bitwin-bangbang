package com.bitwin.bangbang.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitwin.bangbang.admin.domain.Search;
import com.bitwin.bangbang.admin.service.AdminMemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminMemberController {
	private final AdminMemberService memberService;
	// 회원 리스트
	@GetMapping
	public String getMemberList(@RequestParam(value = "p", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyword", defaultValue = "userid") String keyword,
			@RequestParam(value = "search", defaultValue = "_") String search, Model model) {
		model.addAttribute("member", memberService.getMemberList(currentPage, keyword, search));
		return "admin/member/management";
	}

	// 회원정보 상세보기
	@GetMapping("/{uidx}")
	public String getMember(@PathVariable("uidx") int uidx, Model model) {
		model.addAttribute("member", memberService.getMember(uidx));
		return "admin/member/detail";
	}
}
