package com.bitwin.bangbang.member.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginInfo {
	private Long memberId;
	private String username;
	private String name;
	private String profileImage;
	private boolean sns;
}
