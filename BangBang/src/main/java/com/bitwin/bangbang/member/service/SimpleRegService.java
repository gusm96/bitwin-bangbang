package com.bitwin.bangbang.member.service;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.SimpleRegRequest;

@Service
public class SimpleRegService {

	private MemberDao dao;

	@Autowired
	private SqlSessionTemplate template;

	public int insertSimpleMember(SimpleRegRequest regRequest, HttpServletRequest req) {
		int resultCnt = 0;

		SimpleRegRequest simpleReg = null;

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.insertSimpleMember(regRequest);

		return resultCnt;
	}

}
