package com.bitwin.bangbang.member.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	private Long memberId;
	private String username;
	private String password;
	private String name;
	private LocalDate birthday;
	private String phoneNum;
	private String email;
	private boolean emailNotify;
	private boolean smsNotify;
	private boolean snsNotify;
	private String profileImage;
	private LocalDateTime registerDate;
	private boolean sns;

	public LoginInfo getLoginInfo() {
		return LoginInfo.builder()
				.memberId(memberId)
				.username(username)
				.name(name)
				.profileImage(profileImage)
				.build();
	}
}