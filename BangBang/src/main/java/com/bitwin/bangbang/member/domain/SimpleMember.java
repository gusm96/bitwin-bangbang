package com.bitwin.bangbang.member.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleMember {
    private String nickname;
    private String profile;
    private String email;
    private String range;
    private String phoneNum;

    @Builder
    public SimpleMember(String nickname, String profile, String email, String range, String phoneNum) {
        this.nickname = nickname;
        this.profile = profile;
        this.email = email;
        this.range = range;
        this.phoneNum = phoneNum;
    }

    @Builder
    public SimpleMember(String nickname, String profile, String email, String range) {
        this.nickname = nickname;
        this.profile = profile;
        this.email = email;
        this.range = range;
    }

}

