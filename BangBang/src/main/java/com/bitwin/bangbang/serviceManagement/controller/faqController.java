package com.bitwin.bangbang.serviceManagement.controller;

import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.PageDTO;
import com.bitwin.bangbang.serviceManagement.domain.faqVO;
import com.bitwin.bangbang.serviceManagement.service.faqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("admin/faq/*")
public class faqController {

	private faqService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {

		model.addAttribute("list", service.getList(cri));

		int total = service.getTotal(cri);
		
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}

	@PostMapping("/register")
	public String register(faqVO faq, RedirectAttributes rttr) {

		service.register(faq);
		
		rttr.addFlashAttribute("result", faq.getFqidx());
		
		return "redirect:/admin/faq/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("fqidx") int fqidx, @ModelAttribute("cri") Criteria cri, Model model) {
		
		model.addAttribute("faq", service.get(fqidx));
		
	}
	
	@PostMapping("/modify")
	public String modify(faqVO faq, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		
		if(service.modify(faq)) {
			rttr.addFlashAttribute("result", "modify");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());
		
		return "redirect:/admin/faq/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("fqidx") int fqidx, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." + fqidx);
		
		if(service.remove(fqidx)) {
			
			rttr.addFlashAttribute("result", "delete");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());
		
		return "redirect:/admin/faq/list";
	}
}
