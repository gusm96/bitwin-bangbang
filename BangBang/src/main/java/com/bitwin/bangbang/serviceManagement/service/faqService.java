package com.bitwin.bangbang.serviceManagement.service;

import java.util.List;

import com.bitwin.bangbang.serviceManagement.domain.faqVO;
import com.bitwin.bangbang.serviceManagement.domain.Criteria;

public interface faqService {
	
	
	public void register(faqVO faq);
	
	public faqVO get(int fqidx);
	
	public boolean modify(faqVO faq);
	
	public boolean remove(int fqidx);
	
	public List<faqVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	
}
