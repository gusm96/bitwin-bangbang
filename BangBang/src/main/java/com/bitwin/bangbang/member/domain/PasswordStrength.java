package com.bitwin.bangbang.member.domain;

public enum PasswordStrength {
    WEAK("약함"), NORMAL("보통"), STRONG("강함"),VERY_STRONG("매우강함"), ERROR("기본 조건을 충족하세요.");
    private final String value;

    PasswordStrength(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
