package com.bitwin.bangbang.member.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.Member;
import com.bitwin.bangbang.member.domain.MemberPassword;

@Service
@RequiredArgsConstructor
public class MemberCheckService {

	private MemberDao dao;
	
	private final SqlSessionTemplate template;
	
	private final BCryptPasswordEncoder encoder;
	
	public String checkEmail(String email) {
		dao = template.getMapper(MemberDao.class);
		return dao.selectCountByEmail(email) > 0 ? "N" : "Y";
	}
	
	public String checkId(String userId) {
		dao = template.getMapper(MemberDao.class);
		return dao.selectCountByUesrId(userId) > 0 ? "N" : "Y";
	}
	
	public String checkPw(String userid, String currentPw) {
		Member member = null;
		
		dao = template.getMapper(MemberDao.class);
		
		member = dao.selectById(userid);
		
		if(!encoder.matches(currentPw, member.getPassword())) {
			return "N";
		}
		return "Y";
		
	}
}
