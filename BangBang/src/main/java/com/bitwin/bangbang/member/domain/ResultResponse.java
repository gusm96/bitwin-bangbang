package com.bitwin.bangbang.member.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse {
    private int result;
    private String message;

    @Builder
    public ResultResponse(Result result){
        this.result = result.getValue();
        this.message = result.getMessage();
    }
}
