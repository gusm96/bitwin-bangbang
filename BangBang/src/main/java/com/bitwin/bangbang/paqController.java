package com.bitwin.bangbang;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitwin.domain.Criteria;
import com.bitwin.domain.PageDTO;
import com.bitwin.domain.paqVO;
import com.bitwin.service.paqService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@Log4j
@RequestMapping("admin/paq/*")
@AllArgsConstructor
public class paqController {

	private paqService service;

	@GetMapping("/admin-list")
	public void adminList(Criteria cri, Model model) {

		log.info("list: " + cri);

		model.addAttribute("list", service.adminGetList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 200));

		int total = service.adminGetTotal(cri);

		log.info("total: " + total);

		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}

	@GetMapping({ "/admin-get" })
	public void adminGet(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/adminGet or modify");
		model.addAttribute("paq", service.get(pqidx));

	}

	@GetMapping({ "/get", "/reply-register" })
	public void get(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or replyRegister");
		model.addAttribute("paq", service.get(pqidx));

	}

	@PostMapping("/reply-register")
	public String replyRegister(paqVO paq, @ModelAttribute("cri") Criteria cri, @RequestParam("pqidx") int pqidx,
			RedirectAttributes rttr) {

		log.info("modify: " + paq);

		if (service.modify(paq)) {
			rttr.addFlashAttribute("result", "answer");

			rttr.addAttribute("pqidx", pqidx);
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("start", cri.getStart());

			return "redirect:/admin/paq/reply-register";

		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());

		return "redirect:/admin/paq/admin-list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("pqidx") int pqidx, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {

		log.info("remove..." + pqidx);

		if (service.remove(pqidx)) {

			rttr.addFlashAttribute("result", "success");

		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());

		return "redirect:/admin/paq/admin-list";
	}

	@Autowired
	private JavaMailSender mailSender;

	// mailSend
	@RequestMapping(value = "/mailSend", method = RequestMethod.POST)
	public String mailSend(HttpServletRequest request, @RequestParam("pqidx") int pqidx,
			@ModelAttribute("cri") Criteria cri, @RequestParam("uidx") int uidx, RedirectAttributes rttr) {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			String emailReceiver = service.getEmail(uidx);
			messageHelper.setFrom("wnrak0116@gmail.com");
			messageHelper.setTo(emailReceiver);
			messageHelper.setSubject("안녕하세요. 방방술래 고객센터입니다.");
			messageHelper.setText("고객님께서 문의해주신 1:1 문의 답변이 등록되었으니, \"\r\n"
					+ "                         + \"홈페이지에 접속하셔서 확인해주시기 바랍니다.\"\r\n"
					+ "                         + \"감사합니다.");

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pqidx", pqidx);
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());

		return "redirect:/admin/paq/reply-register";
	}

	@PostMapping(value = "/smsSend")
	public String smsSend(HttpServletRequest request, @RequestParam("pqidx") int pqidx,
			@ModelAttribute("cri") Criteria cri, @RequestParam("uidx") int uidx, RedirectAttributes rttr) {

		String api_key = "";
		String api_secret = "";
		Message coolsms = new Message(api_key, api_secret);

		String phoneNum = service.getPhoneNum(uidx);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNum);
		params.put("from", "01037224175");
		params.put("type", "SMS");
		params.put("text", "방방술래에 보내주신 1:1 문의 답변이 완료되었습니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}

		rttr.addFlashAttribute("result", "successSms");
		rttr.addAttribute("pqidx", pqidx);
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("start", cri.getStart());

		return "redirect:/admin/paq/reply-register";

	}

}
