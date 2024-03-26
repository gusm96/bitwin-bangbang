package com.bitwin.bangbang.member.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginRequest {

	private String url;
	private String username;
	private String password;
	private String saveId;

	public LoginParams getLoginParams() {
		return LoginParams.builder()
				.username(this.username)
				.password(this.password)
				.build();
	}

}
