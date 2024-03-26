package com.bitwin.bangbang.member.domain;

import lombok.*;

@Getter
@Builder
public class ApiToken {
    private String access_Token;
    private String refresh_Token;
}
