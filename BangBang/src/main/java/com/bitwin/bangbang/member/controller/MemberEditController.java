package com.bitwin.bangbang.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.member.domain.EditMember;
import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.service.MemberEditService;

@Controller
@RequestMapping("/mypage/edit")
public class MemberEditController {

	@Autowired
	private MemberEditService editService;

	@GetMapping
	public String getMemberEdit(HttpSession session, Model model) {
		// 현재 로그인한 정보를 session 에서 가져온다.
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		int uidx = loginInfo.getUidx();
		model.addAttribute("member", editService.getMember(uidx));
		return "member/mypage/editform";
	}

	@PostMapping
	public String postMemeberEdit(EditMember editMember, HttpServletRequest req)
			throws IllegalStateException, IOException {
		System.out.println(req);
		System.out.println(editMember);
		editService.editMember(editMember, req);
		return "redirect:/mypage";
	}
}
