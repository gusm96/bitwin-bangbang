package com.bitwin.bangbang.member.service;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.MemberRegRequest;

@Service
public class MemberRegService {

	private MemberDao dao;

	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
	private MailSenderService senderService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public int insertMember(MemberRegRequest regRequest, HttpServletRequest req) {
		int resultCnt = 0;

		// 비밀번호 암호화 필요
		String bPw = encoder.encode(regRequest.getPassword());
		// 암호화 된 비밀번호를 저장
		regRequest.setPassword(bPw);
		
		dao = template.getMapper(MemberDao.class);
		
		resultCnt = dao.insertMember(regRequest);
		if (senderService.send(regRequest.getEmail(), regRequest.getUsername()) > 0) {
			System.out.println("메일발송 완료");
		} else {
			System.out.println("메일 발송 실패!");
		}

		return resultCnt;
	}
}
