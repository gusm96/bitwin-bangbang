package com.bitwin.bangbang.member.service;

import com.bitwin.bangbang.member.domain.*;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
    // 회원가입
    MemberDto signUp(SignUpReqDto regRequest);

    // 간편 회원가입
    int simpleSignUp(SimpleSignUpReqDto simpleSignUpReqDto);

    // 이메일로 회원 아이디 찾기
    int searchUsernameByEmail(String email);

    // 임시비밀번호 발급
    int issueTemporaryPassword(PasswordResetRequestDto passwordResetRequestDTO);
    
    // Id로 회원정보 찾기
    Member retrieveMemberById(Long memberId);
    
    // 회원 정보 수정
    int editMemberInfo(EditMember editMember);
    
    // 회원 프로필 이미지 수정
    int editMemberProfileImage(String path, MultipartFile imageFile);
    
    // 회원 비밀번호 수정
    int changePassword(String username, String currentPassword, String newPassword);

    String checkEmail(String email);
    String checkId(String username);
    String checkPw(String username, String currentPw);
}
