package com.bitwin.bangbang.member.service;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.GeneralRegRequest;

@Service
public class GeneralRegService {
	
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int insertMember(GeneralRegRequest regRequest,HttpServletRequest req) {
		int resultCnt =0;
		
		// 비밀번호 암호화 필요
		
		// 메일링 
		
		dao = template.getMapper(MemberDao.class);
		
		resultCnt = dao.insertMember(regRequest);
		
		return resultCnt;
	}
}
