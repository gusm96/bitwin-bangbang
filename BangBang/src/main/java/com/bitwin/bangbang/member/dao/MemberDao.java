package com.bitwin.bangbang.member.dao;

import com.bitwin.bangbang.member.domain.SignUpReqDto;
import com.bitwin.bangbang.member.domain.PasswordResetRequestDto;
import com.bitwin.bangbang.member.domain.SimpleSignUpReqDto;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Select;

import com.bitwin.bangbang.member.domain.EditMember;
import com.bitwin.bangbang.member.domain.LoginInfo;
import com.bitwin.bangbang.member.domain.Member;
import com.bitwin.bangbang.member.domain.MemberPassword;

public interface MemberDao {
    /* Create */
    int save(SignUpReqDto regRequest);

    int simpleSave(SimpleSignUpReqDto regRequest);

    /* Read */
    int countByUsername(String username);

    int countByEmail(String email);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByUsername(String username);

    Optional<LoginInfo> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Member> findAll(int index, int countPerPage);

    List<Member> searchMember(int index, int countPerPage, String keyword, String search);

    String searchId(String email);

    int countByUsernameAndEmail(PasswordResetRequestDto searchPw);

    @Select("select count(*) from user")
    int selectTotalCount();

    /* UPDATE */
    int updateMember(EditMember editMember);

    int changePassword(MemberPassword memberPw);

    void updatePassword2(PasswordResetRequestDto searchPw);

    /* DELETE */
}
