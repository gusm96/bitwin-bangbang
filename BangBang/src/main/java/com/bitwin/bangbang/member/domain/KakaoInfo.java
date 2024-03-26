package com.bitwin.bangbang.member.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoInfo {
    private String tokenUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String logoutRedirectUri;
    private String userInfoRequestUri;
    public KakaoInfo(@Value("${kakao.tokenUrl}") String tokenUrl,
                     @Value("${kakao.client_id}") String clientId,
                     @Value("${kakao.client_secret}") String clientSecret,
                     @Value("${kakao.redirect_uri}") String redirectUri,
                     @Value("${kakao.logout_redirect_uri}") String logoutRedirectUri,
                     @Value("${kakao.userinfo_request_uri}")String userInfoRequestUri) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.logoutRedirectUri = logoutRedirectUri;
        this.userInfoRequestUri = userInfoRequestUri;
    }
} 
