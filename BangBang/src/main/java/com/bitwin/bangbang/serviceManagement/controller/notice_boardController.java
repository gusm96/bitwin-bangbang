package com.bitwin.bangbang.serviceManagement.controller;


import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.PageDTO;
import com.bitwin.bangbang.serviceManagement.domain.notice_boardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitwin.bangbang.serviceManagement.service.notice_boardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/admin/notice_board/*")
public class notice_boardController {
	
	
	private notice_boardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		
		
		model.addAttribute("list", service.getList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 200));
		
		int total = service.getTotal(cri);
		
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));

		
	}
	
	

	
	
	
	
	
	@PostMapping("/register")
	public String register(notice_boardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getNidx());
		
		return "redirect:/admin/notice_board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("nidx") int nidx, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("/get or modify");
		model.addAttribute("board", service.get(nidx));
		
	}
	
	@PostMapping("/modify")
	public String modify(notice_boardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify: " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "modify");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());
		
		return "redirect:/admin/notice_board/list";
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("nidx") int nidx, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove..." + nidx);
		
		if(service.remove(nidx)) {
			
			rttr.addFlashAttribute("result", "delete");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());
		
		return "redirect:/admin/notice_board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	
	
	
	
	
	
	
}
