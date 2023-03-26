package com.bitwin.bangbang.serviceManagement.controller;


import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.PageDTO;
import com.bitwin.bangbang.serviceManagement.service.faqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("member/faq/*")
@AllArgsConstructor
public class member_faqController {
	
	private faqService service;
	
	
	
	@GetMapping("/member-list")
	public void memberList(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		
		
		model.addAttribute("list", service.getList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 200));
		
		int total = service.getTotal(cri);
		
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		
	}
	
	
	
	
	
	
	@GetMapping({"/get", "/modify", "/member-get"})
	public void get(@RequestParam("fqidx") int fqidx, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("/get or modify");
		model.addAttribute("faq", service.get(fqidx));
		
	}
	
	
	
	
	
	
	

	
	
	
	
}
