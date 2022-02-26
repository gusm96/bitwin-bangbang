package com.bitwin.bangbang.store.service;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.exception.LoginInvalidException;
import com.bitwin.bangbang.store.dao.StoreDao;
import com.bitwin.bangbang.store.domain.Store;
import com.bitwin.bangbang.store.domain.StoreLoginInfo;
import com.bitwin.bangbang.store.domain.StoreLoginRequest;

@Service
public class StoreService {

	private StoreDao dao;

	@Autowired
	private SqlSessionTemplate template;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public String storeLogin(StoreLoginRequest loginReq, HttpSession session) throws LoginInvalidException {
		String viewPage = "";

		Store store = null;
		StoreLoginInfo loginInfo = new StoreLoginInfo();
		dao = template.getMapper(StoreDao.class);

		store = dao.selectByStoreId2(loginReq.getStoreId());
		System.out.println("store : " + store);

		if (store == null) {
			// 아이디 또는 비밀번호 확인
			throw new LoginInvalidException("아이디 또는 비밀번호를 확인하세요.");
		} else if (!bcrypt.matches(loginReq.getStorePw(), store.getStorePw())) {
			// 아이디 또는 비밀번호 확인
			throw new LoginInvalidException("아이디 또는 비밀번호를 확인하세요.");
		} else {
			loginInfo.setSidx(store.getSidx());
			loginInfo.setSname(store.getSname());
			loginInfo.setStoreId(store.getStoreId());
			loginInfo.setPhoto(store.getPhoto());
			session.setAttribute("storeInfo", loginInfo);
			viewPage = "redirect:/store";
		}

		return viewPage;
	}
	public Store getStoreInfo(HttpSession session) {
		Store store = null;
		StoreLoginInfo loginInfo = (StoreLoginInfo) session.getAttribute("storeInfo");
		String storeId = loginInfo.getStoreId();
		
		dao = template.getMapper(StoreDao.class);
		
		store = dao.selectByStoreId2(storeId);
		
		return store;
	}
}
