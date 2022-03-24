package com.bitwin.bangbang.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bitwin.bangbang.store.domain.Store;
import com.bitwin.bangbang.store.domain.StoreEditRequest;
import com.bitwin.bangbang.store.domain.StoreEditRequestList;
import com.bitwin.bangbang.store.domain.StoreOrderInfo;
import com.bitwin.bangbang.store.domain.StorePassword;
import com.bitwin.bangbang.store.domain.StoreRegRequest;
import com.bitwin.bangbang.store.domain.StoreSearchPassword;

public interface StoreDao {
	
	// 가맹점 등록
	public int insertStore(StoreRegRequest regRequest);
	
	// 모든 가맹점 정보
	public List<Store> selectAll(int index, int cOUNT_PER_PAGE);
	
	// 가맹점 아이디
	public int selectByStoreId(String storeId);

	public Store selectBySname(String sname);

	public Store selectByStoreId2(String storeId);

	public StoreOrderInfo selectOrderInfo (int sidx);

	public int updatePassword(StorePassword storePw);

	public int storeInfoEditRequest(StoreEditRequest editRequest);

	public List<StoreEditRequestList> selectEditRequestList(int index, int cOUNT_PER_PAGE);

	public StoreEditRequest selectStoreEditReq(int sridx);

	public int updateStore(StoreEditRequest editRequest);

	public void deleteEditReq(int sidx);

	public int selectCountByEmail(String email);

	public String searchId(String email);

	public int selectCountByEmailStoreId(StoreSearchPassword searchPw);

	public void updatePassword2(StoreSearchPassword searchPw);
	
	@Select("select count(*) from store")
	public int selectTotalCount();
	@Select("select count(*) from store_req")
	public int editReqTotalCount();
}
