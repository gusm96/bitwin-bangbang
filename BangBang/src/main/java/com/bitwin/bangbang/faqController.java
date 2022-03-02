package com.bitwin.bangbang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitwin.domain.faqVO;
import com.bitwin.domain.Criteria;
import com.bitwin.domain.PageDTO;
import com.bitwin.service.faqService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("admin/faq/*")
@AllArgsConstructor
public class faqController {
	
	private faqService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		
		
		model.addAttribute("list", service.getList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 200));
		
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
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	

	
	
	
	
	
	
	
}
