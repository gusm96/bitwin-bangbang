package com.bitwin.bangbang.member.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizationRequest {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=").append(grantType);
        sb.append("&client_id=").append(clientId);
        sb.append("&client_secret=").append(clientSecret);
        sb.append("&redirect_uri=").append(redirectUri);
        sb.append("&code=").append(code);
        return sb.toString();
    }
}
