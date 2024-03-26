package com.bitwin.bangbang.member.domain;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberOrderInfo {
	private Long memberId;
	private String username;
	private String phoneNum;
}
