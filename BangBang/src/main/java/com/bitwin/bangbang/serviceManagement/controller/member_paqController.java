package com.bitwin.bangbang.serviceManagement.controller;

import javax.servlet.http.HttpSession;

import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.PageDTO;
import com.bitwin.bangbang.serviceManagement.domain.paqVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.serviceManagement.service.paqService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/paq/*")
public class member_paqController {

	private paqService service;

	@GetMapping("/member-list")
	public String memberList(Criteria cri, Model model, HttpSession session) {

		log.info("list: " + cri);

		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		cri.setUidx(loginInfo.getUidx());
		model.addAttribute("list", service.memberGetList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 200));

		int total = service.memberGetTotal(cri);

		log.info("total: " + total);

		model.addAttribute("pageMaker", new PageDTO(cri, total));

		return "member/paq/member-list";
	}

	@GetMapping({ "/get", "/reply-register" })
	public void get(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or replyRegister");
		model.addAttribute("paq", service.get(pqidx));

	}

	@GetMapping("/member-register")
	public void memberRegister() {

	}

	@PostMapping("/member-register")
	public String register(paqVO paq, RedirectAttributes rttr, HttpSession session) {

		log.info("register: " + paq);
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		paq.setUidx(loginInfo.getUidx());
		service.register(paq);

		rttr.addFlashAttribute("result", paq.getPqidx());

		return "redirect:/member/paq/member-list";
	}

	@GetMapping({ "/member-get" })
	public void memberGet(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/adminGet or modify");
		model.addAttribute("paq", service.get(pqidx));

	}
}
