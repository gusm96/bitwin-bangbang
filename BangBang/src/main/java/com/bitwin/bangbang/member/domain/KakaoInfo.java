package com.bitwin.bangbang.member.domain;

import lombok.Getter;

@Getter
public class KakaoInfo {
	private String tokenURL = "https://kauth.kakao.com/oauth/token";
	private String client_id = "a085f34768fc66402a5576852c5c8a96";
	private String client_secret ="1JQ2oztoPHxk8NHjSPD70PNkvoe56Miu";
	private String redirect_uri = "http://localhost:8080/bangbang/member/login/oauth/kakao";
	private String logout_redirect_uri ="http://localhost:8080/bangbang/member/logout/oauth/kakao";
} 
