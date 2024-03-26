package com.bitwin.bangbang.member.service;

import com.bitwin.bangbang.member.domain.MemberLoginRequest;
import com.bitwin.bangbang.member.exception.LoginInvalidException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface MemberLoginService {

    String login(MemberLoginRequest loginRequest, HttpServletResponse res, HttpSession session) throws LoginInvalidException;

    String socialLogin(HttpSession httpSession, String snsName, String code) throws LoginInvalidException;
}
