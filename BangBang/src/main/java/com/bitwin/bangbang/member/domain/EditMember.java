package com.bitwin.bangbang.member.domain;

import lombok.*;
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EditMember {
	private String phoneNum;
	private String email;
	private boolean emailNotify;
	private boolean mobileNotify;
	private boolean snsNotify;
}
