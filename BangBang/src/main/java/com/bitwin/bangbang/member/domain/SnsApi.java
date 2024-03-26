package com.bitwin.bangbang.member.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsApi {
    private String tokenURL;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
