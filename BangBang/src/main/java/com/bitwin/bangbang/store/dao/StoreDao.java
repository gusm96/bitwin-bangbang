package com.bitwin.bangbang.store.dao;

import java.util.List;

import com.bitwin.bangbang.store.domain.Store;

public interface StoreDao {
	
	// 가맹점 등록
	public int insertStore(Store store);
	
	// 모든 가맹점 정보
	public List<Store> selectAll();

}
