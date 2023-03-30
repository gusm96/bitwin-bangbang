package com.bitwin.bangbang.member.service;

import com.bitwin.bangbang.member.domain.PasswordStrength;
import org.springframework.stereotype.Service;

@Service
public class PasswordStrengthCheck {
    public PasswordStrength ConfirmPasswordConditions(String password){
        try {
            int numberMet = getNumberMet(password);
            PasswordStrength result = getPasswordStrength(numberMet);
            return result;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return PasswordStrength.ERROR;
        }
    }

    private static int getNumberMet(String password) {
        int result = 0;
        if(isMeetBasicConditions(password)){
            result++;
            if(isIncludeNumber(password)) result++;
            if(isIncludeUppercase(password)) result++;
            if(isIncludeSpecialSymbols(password)) result++;
        }

        return result;
    }
    // 기본 조건 비밀번호 8자 이상 16자 미만
    private static boolean isMeetBasicConditions(String password){
        if (password.length() >= 8 && password.length() <= 16 && !password.isEmpty()) {
            return true;
        }
        return false;
    }
    private static boolean isIncludeSpecialSymbols(String password) {
        if(!password.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|(|)|.|-]*")) return true;
        return false;
    }

    private static boolean isIncludeUppercase(String password) {
        for(char val : password.toCharArray()){
            if(Character.isUpperCase(val)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncludeNumber(String password) {
        for(char val : password.toCharArray()){
            int n = val - '0';
            if(n >= 0 && n <= 9){
                return true;
            }
        }
        return false;
    }

    private static PasswordStrength getPasswordStrength(int numberMet) {
        if(numberMet == 1){
            // WEAK;
            return PasswordStrength.WEAK;
        }else if (numberMet == 2) {
            // NORMAL
            return PasswordStrength.NORMAL;
        } else if (numberMet == 3) {
            // STRONG
            return PasswordStrength.STRONG;
        } else if (numberMet == 4) {
            // VERY_STRONG
            return PasswordStrength.VERY_STRONG;
        }else {
            return PasswordStrength.ERROR;
        }

    }
}
