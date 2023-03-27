package com.bitwin.bangbang.serviceManagement.service;

import java.util.List;

import com.bitwin.bangbang.serviceManagement.domain.Criteria;
import com.bitwin.bangbang.serviceManagement.domain.faqVO;
import com.bitwin.bangbang.serviceManagement.mapper.faqMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class faqServiceImpl implements faqService {
	
	//Spring 4.3 이상에서 자동 처리
	private faqMapper mapper;
	
	@Override
	public void register(faqVO faq) {
		log.info("register......" + faq);
		mapper.insertSelectKey(faq);
		
	}
	
	@Override
	public List<faqVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
		
	}
	
	@Override
	public faqVO get(int fqidx) {
		
		log.info("get......" + fqidx);
		
		return mapper.read(fqidx);
		
	}
	
	@Override
	public boolean modify(faqVO faq) {
		
		log.info("modify....." + faq);
		
		return mapper.update(faq) == 1;
		
	}
	
	@Override
	public boolean remove(int fqidx) {
		
		log.info("remove...." + fqidx);
		
		return mapper.delete(fqidx)==1;
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
	
	
	
	
	
	
	
	
}
