package com.bitwin.bangbang.member.domain;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPassword {
	private String username;
	private String newPassword;
}
