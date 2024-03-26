package com.bitwin.bangbang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Result {
    SIGNUP_SUCCESS(1,"회원가입을 성공했습니다."),
    LOGIN_SUCCESS(1, "로그인을 성공했습니다."),
    FAILED(0, null);
    private int value;
    private String message;

    public Result setMessage(String message){
        this.message = message;
        return this;
    }
}
