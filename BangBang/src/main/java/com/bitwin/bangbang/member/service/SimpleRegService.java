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

	@Autowired
	private MailSenderService mailSender;

	public int insertSimpleMember(SimpleRegRequest regRequest, HttpServletRequest req) {
		int resultCnt = 0;

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.insertSimpleMember(regRequest);
		if (mailSender.send(regRequest.getEmail(), regRequest.getUsername()) > 0) {
			System.out.println("메일전송 성공");
		} else {
			System.out.println("메일전송 실패");
		}

		return resultCnt;
	}

}
