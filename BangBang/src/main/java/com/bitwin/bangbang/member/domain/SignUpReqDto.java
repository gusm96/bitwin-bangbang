package com.bitwin.bangbang.member.domain;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpReqDto {

    @NotBlank(message = "아이디는 5자 이상 20자 이하로 작성하세요.")
    @Size(min = 5, max = 20)
    private String username;
    @NotBlank(message = "비밀번호는 8자 이상 16자 이하로 작성하세요.")
    @Size(min = 8, max = 16)
    private String password;
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    private LocalDate birthday;
    private String phoneNum;
    @Email
    private String email;
    private boolean emailNotify;
    private boolean smsNotify;
    private boolean snsNotify;


    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
