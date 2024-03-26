package com.bitwin.bangbang.member.exception;

public class ExistingMemberException extends RuntimeException {
    public ExistingMemberException (String message){
        super(message);
    }
}
