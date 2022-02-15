package com.bitwin.bangbang.member.dao;

import com.bitwin.bangbang.member.domain.MemberRegRequest;
import com.bitwin.bangbang.member.domain.Member;

public interface MemberDao {
	
	public int insertMember (MemberRegRequest regRequest);
	
	public int selectCountByUesrId(String userId);
	
	public int selectCountByEmail(String email);
	
	public Member selectById(String userid);
	
	public Member selectByIdx(int uidx);
	
//	public Member selectByIdPw (String userid, String pw);
}
