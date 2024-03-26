package com.bitwin.bangbang.member.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.");
    private String errorMessage;
}
