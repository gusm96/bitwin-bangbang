package com.bitwin.bangbang.member.dao;

import com.bitwin.bangbang.member.domain.MemberRegRequest;
import com.bitwin.bangbang.member.domain.SearchPassword;
import com.bitwin.bangbang.member.domain.SimpleRegRequest;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bitwin.bangbang.admin.domain.Search;
import com.bitwin.bangbang.member.domain.EditMember;
import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.domain.Member;
import com.bitwin.bangbang.member.domain.MemberPassword;

public interface MemberDao {

	public int insertMember(MemberRegRequest regRequest);

	public int insertSimpleMember(SimpleRegRequest regRequest);

	public int selectCountByUesrId(String userId);

	public int selectCountByEmail(String email);

	public Member selectById(String userid);

	public Member selectByIdx(int uidx);

	public LoginInfo selectByEmail(String email);

	public int editMember(EditMember editMember);

	public int updatePassword(MemberPassword memberPw);

	public List<Member> selectAll(int index, int cOUNT_PER_PAGE);
	// 검색기능
	public List<Member> searchMember(int index, int cOUNT_PER_PAGE, String keyword, String search);
	
	public String searchId(String email);

	public int selectCountByEmailUserId(SearchPassword searchPw);

	public void updatePassword2(SearchPassword searchPw);

	@Select("select count(*) from user")
	public int selectTotalCount();


}
