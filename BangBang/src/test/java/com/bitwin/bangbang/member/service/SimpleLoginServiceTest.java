package com.bitwin.bangbang.member.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bitwin.bangbang.member.domain.Member;


public class SimpleLoginServiceTest {

	@Test
	void login() {
		Member member1 = new Member(1, "gusm96", "test", "test1", "1996-08-19", "010-1111-111", "gusm96@naver.com",
				false, false, true, null, "2023-03-15", false);
		Member member2 = new Member(1, "gusm96", "test", "test1", "1996-08-19", "010-1111-111", "gusm96@naver.com",
				false, false, true, null, "2023-03-15", false);
		assertEquals(member1, member2);
		
		System.out.println("test");
	}
}
