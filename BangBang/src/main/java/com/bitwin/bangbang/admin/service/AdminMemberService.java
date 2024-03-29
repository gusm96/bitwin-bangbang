package com.bitwin.bangbang.admin.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.admin.domain.MemberListView;
import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.Member;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

	private MemberDao dao;

	private final SqlSessionTemplate template;

	// 페이지 당 표현할 맴버수
	private final int COUNT_PER_PAGE = 15;

	// 페이징 번호 노출 개수
	private final int COUNT_PER_PAGING_NUM = 5;

	public Member getMember(int uidx) {
		Member member = null;
		dao = template.getMapper(MemberDao.class);
		member = dao.selectByIdx(uidx);
		return member;
	}

	public MemberListView getMemberList(int currentPage, String keyword, String search) {
		List<Member> list = null;
		dao = template.getMapper(MemberDao.class);
		int totalCount = dao.selectTotalCount();
		int index = (currentPage - 1) * COUNT_PER_PAGE;
		list = dao.searchMember(index, COUNT_PER_PAGE, keyword, search);
		return new MemberListView(currentPage, COUNT_PER_PAGE, COUNT_PER_PAGING_NUM, list, totalCount);
	}
}
