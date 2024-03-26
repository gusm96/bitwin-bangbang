package com.bitwin.bangbang.member.domain;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
    private Long id;
    private String username;
    private String name;
    private LocalDate birthday;
    private String email;
    private String phoneNum;
    private boolean emailNotify;
    private boolean smsNotify;
    private boolean snsNotify;
    private String profileImage;
    private LocalDateTime registerDate;
    private boolean sns;


}
