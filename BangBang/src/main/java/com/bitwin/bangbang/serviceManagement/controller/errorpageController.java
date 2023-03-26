package com.bitwin.bangbang.serviceManagement.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("error")
public class errorpageController {
	
	@GetMapping("/errorpage")
	public String errorpage() {
		return "/bangbang/error";
	}
}
