package com.bitwin.bangbang.store.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.store.dao.StoreDao;
import com.bitwin.bangbang.store.dao.StoreLoginInfo;
import com.bitwin.bangbang.store.domain.Store;

@Service
public class StoreService {

	private StoreDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public Store getStoreInfo(StoreLoginInfo loginInfo) {
		Store store = null;
		
		dao = template.getMapper(StoreDao.class);
		
		store = dao.selectByStoreId2(loginInfo.getStoreId());
		
		return store;
	}
}
