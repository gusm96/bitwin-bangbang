package com.bitwin.bangbang.store.dao;

import java.util.List;

import com.bitwin.bangbang.store.domain.Store;
import com.bitwin.bangbang.store.domain.StoreOrderInfo;
import com.bitwin.bangbang.store.domain.StorePassword;
import com.bitwin.bangbang.store.domain.StoreRegRequest;

public interface StoreDao {
	
	// 가맹점 등록
	public int insertStore(StoreRegRequest regRequest);
	
	// 모든 가맹점 정보
	public List<Store> selectAll();
	
	
	// 가맹점 아이디
	public int selectByStoreId(String storeId);

	public Store selectBySname(String sname);

	public Store selectByStoreId2(String storeId);

	public StoreOrderInfo selectOrderInfo (int sidx);

	public int updatePassword(StorePassword storePw);
}
