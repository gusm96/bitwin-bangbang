package com.bitwin.bangbang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiToken {
    private String access_Token;
    private String refresh_Token;
}
