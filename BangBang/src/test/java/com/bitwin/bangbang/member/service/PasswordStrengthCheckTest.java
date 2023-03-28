package com.bitwin.bangbang.member.service;

import com.bitwin.bangbang.member.domain.PasswordStrength;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordStrengthCheckTest {
    // 비밀번호 강도 테스트
    // 1. 비밀번호가 8자 이상, 16자 이하 인가?
    // 2. 숫자가 포함되었는가?
    // 3. 대문자가 포함되었는가?
    // 4. 특수기호가 포함 되었는가?
    // 5. 공백은 없는가?
    // 공백 및 Null 값은 NullpointException
    // 비밀번호 강도는 3단계로 구분
    // 조건 1개 만족시 WEAK
    // 조건 2개 만족시 NORMAL
    // 조건 3개 만족시 STRONG

    // 비밀번호 길이 테스트
    private PasswordStrengthCheck passwordStrengthCheck = new PasswordStrengthCheck();
    private String getPasswordStrength(String password) {
        String result = passwordStrengthCheck.ConfirmPasswordConditions(password).getValue();
        return result;
    }
    @Test
    public void meetPasswordlength(){
        String password = "testtest";
        assertEquals("약함", getPasswordStrength(password));
    }
    @Test    public void includeNumbers(){
        String password = "asdf1234";
        assertEquals("보통", getPasswordStrength(password));
    }
    @Test
    public void includeUppercase(){
        String password = "ABCDabcd";
        String password2 = "ABCD12345";
        assertEquals("보통", getPasswordStrength(password));
        assertEquals("강함", getPasswordStrength(password2));
    }
    @Test
    public void includeSpecialSymbols(){
        String password = "@@abcdabcd";
        String password2 = "@@ABcd1234";
        String password3 = "!@#$!@#$";
        assertEquals("보통", getPasswordStrength(password));
        assertEquals("매우강함", getPasswordStrength(password2));
        assertEquals("보통", getPasswordStrength(password3));
    }

    @Test
    public void passwordError(){
        // 비밀번호가 기본 조건을 충족하지 못하거나 공백/Null 일 경우
        String password = "abcd";
        String password2 = "";
        String password3 = null;
        assertEquals("기본 조건을 충족하세요.",getPasswordStrength(password));
        assertEquals("기본 조건을 충족하세요.",getPasswordStrength(password2));
        assertEquals("기본 조건을 충족하세요.",getPasswordStrength(password3));

    }
}
