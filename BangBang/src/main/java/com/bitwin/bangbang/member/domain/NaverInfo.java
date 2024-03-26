package com.bitwin.bangbang.member.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverInfo {

    private String tokenUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String userInfoRequestUri;

    public NaverInfo(@Value("${naver.tokenUrl}") String tokenUrl,
                     @Value("${naver.client_id}") String clientId,
                     @Value("${naver.client_secret}") String clientSecret,
                     @Value("${naver.redirect_uri}") String redirectUri,
                     @Value("${naver.userinfo_request_uri}") String userInfoRequestUri) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.userInfoRequestUri = userInfoRequestUri;
    }
}
