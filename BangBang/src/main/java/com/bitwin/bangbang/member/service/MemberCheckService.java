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
	

}
