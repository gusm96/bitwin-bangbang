package com.bitwin.bangbang.member.service;

public class PasswordStrongMeter {
	public PasswordStrong meter(String password) {
		int count = 0;
		boolean lengthIsMeet = false;
		boolean containingNumbers = false;
		boolean containingSpecialSybols = false;
		if(password != null || !password.isEmpty() || password.length() > 8){
			lengthIsMeet = true;
			count++;
		}
		for(char val : password.toCharArray()){
			if(val >=0 && val <= 9){
				containingNumbers = true;
				count++;
				break;
			}
		}
		return PasswordStrong.STRONG;
	}
}
