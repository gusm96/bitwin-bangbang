package com.bitwin.bangbang.member.domain;

import lombok.Getter;

@Getter
public class NaverInfo {
	private String tokenURL = "https://nid.naver.com/oauth2.0/token";
	private String client_id = "KDtI_xrcHtNm3MgYwc_T";
	private String client_secret = "pu3PnI2a4z";
	private String redirect_uri = "http://localhost:8080/bangbang/member/login/oauth/naver";
}
