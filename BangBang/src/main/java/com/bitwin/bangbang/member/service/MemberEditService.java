package com.bitwin.bangbang.member.service;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.Member;

@Service
public class MemberEditService {
	
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public Member getMember(int uidx) {
		dao = template.getMapper(MemberDao.class);
		return dao.selectByIdx(uidx);
	}
	
	
}
