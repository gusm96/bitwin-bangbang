package com.bitwin.bangbang.member.dao;

import org.apache.ibatis.annotations.Insert;

import com.bitwin.bangbang.member.domain.GeneralRegRequest;
import com.bitwin.bangbang.member.domain.Member;

public interface MemberDao {
	
	public int insertMember (GeneralRegRequest regRequest);
	
	public int selectCountByUesrId(String userId);
	
	public int selectCountByEmail(String email);
	
//	public Member selectByIdPw (String userid, String pw);
}
