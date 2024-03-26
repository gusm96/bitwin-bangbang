package com.bitwin.bangbang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum MemberRouter {
    SIGNUP_SUCCESS_REDIRECT("member/signupResult");

    private String router;
}
