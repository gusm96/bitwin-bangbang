package com.bitwin.bangbang.admin.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.store.dao.StoreDao;
import com.bitwin.bangbang.store.domain.Store;

@Service
public class AdminStoreService {
	private StoreDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int insertStore (Store store) {
		int resultCnt = 0;
		
		dao = template.getMapper(StoreDao.class);
		
		resultCnt = dao.insertStore(store);
		
		return resultCnt;
	}
	
}
