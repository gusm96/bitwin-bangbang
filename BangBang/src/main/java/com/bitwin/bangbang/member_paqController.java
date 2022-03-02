package com.bitwin.bangbang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import com.bitwin.domain.paqVO;
import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.domain.Criteria;
import com.bitwin.domain.PageDTO;
import com.bitwin.service.paqService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/paq/*")
@AllArgsConstructor
public class member_paqController {
	
	private paqService service;
	

	
	@GetMapping("/member-list")
	public String memberList(Criteria cri, Model model , HttpSession  session) {
		
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
	
	
	  @GetMapping({"/get", "/reply-register"})
		public void get(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {
			
			log.info("/get or replyRegister");
			model.addAttribute("paq", service.get(pqidx));
			
		}
	
	  
		@GetMapping("/member-register")
		public void memberRegister() {
			
		}
	
	@PostMapping("/member-register")
	public String register(paqVO paq, RedirectAttributes rttr , HttpSession  session) {
		
		log.info("register: " + paq);
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		paq.setUidx(loginInfo.getUidx());
		service.register(paq);
		
		rttr.addFlashAttribute("result", paq.getPqidx());
		
		return "redirect:/member/paq/member-list";
	}
	
	@GetMapping({"/member-get"})
	public void memberGet(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {
		
		
		log.info("/adminGet or modify");
		model.addAttribute("paq", service.get(pqidx));
		
	}
	
	
	
	
	
	  
	  
	
	

		
	
	
	
	
	
	
	
    
    
    
	@Autowired
	private JavaMailSender mailSender;
    
	//mailSend 肄붾뱶
	@RequestMapping(value = "/mailSend", method = RequestMethod.POST)
	public String mailSend(HttpServletRequest request,@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, @RequestParam("uidx") int uidx, RedirectAttributes rttr) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		    String emailReceiver = service.getEmail(uidx);
		    messageHelper.setFrom("wnrak0116@gmail.com"); // 蹂대궡�뒗�궗�엺 �씠硫붿씪 �뿬湲곗꽑 google 硫붿씪�꽌踰� �궗�슜�븯�뒗 �븘�씠�뵒瑜� �옉�꽦�븯硫대맖
		    messageHelper.setTo(emailReceiver); // 諛쏅뒗�궗�엺 �씠硫붿씪
		    messageHelper.setSubject("�븞�뀞�븯�꽭�슂. 諛⑸갑�닠�옒 怨좉컼�꽱�꽣�엯�땲�떎."); // 硫붿씪�젣紐�
		    messageHelper.setText("怨좉컼�떂猿섏꽌 臾몄쓽�빐二쇱떊 1:1 臾몄쓽 �떟蹂��씠 �벑濡앸릺�뿀�쑝�땲, "
		    					+ "�솃�럹�씠吏��뿉 �젒�냽�븯�뀛�꽌 �솗�씤�빐二쇱떆湲� 諛붾엻�땲�떎."
		    					+ "媛먯궗�빀�땲�떎."); // 硫붿씪 �궡�슜
		    
		    mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 rttr.addFlashAttribute("result", "success");
		 rttr.addAttribute("pqidx", pqidx); 
		 rttr.addAttribute("pageNum", cri.getPageNum()); 
		 rttr.addAttribute("amount", cri.getAmount()); 
		 rttr.addAttribute("start", cri.getStart());
		 
		
		return "redirect:/member/paq/reply-register";
	}
	
	
	
}
