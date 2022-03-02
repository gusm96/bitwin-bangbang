package com.bitwin.bangbang.temp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitwin.bangbang.order.domain.MyStore;

@Controller
public class TempController {

	@RequestMapping("/temp")
	public String temp(Model model) {

		return "order/temp/home";
	}

	@RequestMapping("/item-page")
	public String itemPage(Model model) {

		return "order/temp/item_page";
	}

	@RequestMapping("/login/member")
	public String login(HttpSession session, HttpServletRequest request) {
		session.setAttribute("loginInfo", new LoginInfo(1, "일반회원1"));
		session.setAttribute("myStore", new MyStore(1, 1));

		return "order/temp/login";
	}

	@RequestMapping("/login/store1")
	public String login2(HttpSession session, HttpServletRequest request) {
		session.setAttribute("storeInfo", new StoreInfo(1, "가맹점1"));

		return "order/temp/login2";
	}

	@RequestMapping("/login/admin1")
	public String login3(HttpSession session, HttpServletRequest request) {
		session.setAttribute("adminInfo", new AdminInfo(1, "관리자1"));

		return "order/temp/login3";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "order/temp/login";
	}

	@RequestMapping("/login_req")
	public String loginReq() {

		return "order/temp/login_req";
	}

	@RequestMapping("/login_req2")
	public String loginReq2() {

		return "order/temp/login_req2";
	}

	@RequestMapping("/login_req3")
	public String loginReq3() {

		return "order/temp/login_req3";
	}

	@RequestMapping("/temp/order-list")
	public String orderList() {

		return "order/temp/order_list";
	}

	@RequestMapping("/temp/map")
	public String showMap() {

		return "order/temp/map";
	}

}
