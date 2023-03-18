package com.bitwin.bangbang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SnsApi {

    private String tokenURL;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
