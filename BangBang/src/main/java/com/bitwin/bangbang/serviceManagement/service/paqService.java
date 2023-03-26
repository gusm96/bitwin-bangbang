package com.bitwin.bangbang.serviceManagement.service;

import java.util.List;

import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.paqVO;

public interface paqService {
	
	
	public void register(paqVO paq);
	
	public paqVO get(int pqidx);
	
	public boolean modify(paqVO paq);
	
	public boolean remove(int pqidx);
	
	public List<paqVO> memberGetList(Criteria cri);
	
	public List<paqVO> adminGetList(Criteria cri);
	
	public int memberGetTotal(Criteria cri);
	
	public int adminGetTotal(Criteria cri);

	public String getEmail(int uidx);
	
	public String getPhoneNum(int uidx);

	
	
}
