package com.bitwin.bangbang.member.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class PasswordStrongMeterTest {
	// Password 조건
	// 1. 8글자 이상 인가?
	// 2. 대문자가 포함 되었는가?
	// 3. 숫자가 포함 되었는가?
	// 4. 특수기호가 포함 되었는가?
	// 5. Null or isEmpty 가 아닌가 ?
	private PasswordStrongMeter meter = new PasswordStrongMeter();

	private void pwAssertEquals(PasswordStrong condition, String password) {
		PasswordStrong result = meter.meter(password);
		assertEquals(condition, result);
	}

	@Test
	@DisplayName("Password가 모든 조건에 충족할 때")
	public void meetAllConditions() {
		pwAssertEquals(PasswordStrong.STRONG, "Abcde1234@");
	}

	@Test
	@DisplayName("Password가 null이거나 \"\"일때")
	public void passwordNullOrIsEmpty() {
		pwAssertEquals(PasswordStrong.WEAK, "");
		pwAssertEquals(PasswordStrong.WEAK, null);
	}

	@Test
	@DisplayName("Password가 8자 이상인가?")
	public void meetPasswordLength() {
		pwAssertEquals(PasswordStrong.NORMAL, "abcd"); // error
		pwAssertEquals(PasswordStrong.NORMAL, "abcdefg123");
	}

	@Test
	@DisplayName("Password에 숫자가 포함되었는가?")
	public void PasswordContainingNumbers(){
		pwAssertEquals(PasswordStrong.NORMAL, "abcd1234");
	}
}
