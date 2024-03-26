package com.bitwin.bangbang.member.service.implement;

import com.bitwin.bangbang.member.domain.*;
import com.bitwin.bangbang.member.exception.ExistingMemberException;
import com.bitwin.bangbang.member.service.MailSender;
import com.bitwin.bangbang.member.service.MemberService;
import com.bitwin.bangbang.member.service.RandomPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberDao dao;
    private final SqlSessionTemplate template;
    private final RandomPassword randomPw;
    private final MailSender mailSender;
    private final BCryptPasswordEncoder bcrypt;

    // 회원가입
    @Override
    public MemberDto signUp(SignUpReqDto signUpReqDto) {
        // 회원정보 검증
        validateUsernameAndEmail(signUpReqDto.getUsername(), signUpReqDto.getEmail());
        // 비밀번호 암호화
        String bPw = bcrypt.encode(signUpReqDto.getPassword());
        // 암호화 된 비밀번호를 저장
        signUpReqDto.setEncodedPassword(bPw);
        try {
            dao = template.getMapper(MemberDao.class);
            // 회원 정보 DB 에 insert
            dao.save(signUpReqDto);
            return MemberDto.builder()
                    .username(signUpReqDto.getUsername())
                    .email(signUpReqDto.getEmail())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("회원가입을 실패했습니다.");
        }
    }

    private void validateUsernameAndEmail(String username, String email) {
        if (isUsernameDuplicated(username))
            throw new ExistingMemberException("이미 존재하는 아이디 입니다.");
        // email 중복
        if (isEmailDuplicated(email))
            throw new ExistingMemberException("이미 존재하는 이메일 주소 입니다.");
    }

    private boolean isUsernameDuplicated(String username) {
        dao = template.getMapper(MemberDao.class);
        return dao.existsByUsername(username);
    }

    private boolean isEmailDuplicated(String email) {
        dao = template.getMapper(MemberDao.class);
        return dao.existsByEmail(email);
    }

    // 간편 회원가입
    @Override
    public int simpleSignUp(SimpleSignUpReqDto regRequest) {
        int resultCnt = 0;
        dao = template.getMapper(MemberDao.class);
        resultCnt = dao.simpleSave(regRequest);
        if (mailSender.send(regRequest.getEmail(), regRequest.getUsername()) > 0) {
            System.out.println("메일전송 성공");
        } else {
            System.out.println("메일전송 실패");
        }

        return resultCnt;
    }

    @Override
    public int searchUsernameByEmail(String email) {
        int resultCnt = 0;
        dao = template.getMapper(MemberDao.class);
        if (dao.countByEmail(email) > 0) {
            // 메일로 회원의 아이디 전송
            String username = dao.searchId(email);
            resultCnt = mailSender.sendId(email, username);
        }
        return resultCnt;
    }

    @Override
    public int issueTemporaryPassword(PasswordResetRequestDto passwordResetRequestDTO) {
        int resultCnt = 0;
        dao = template.getMapper(MemberDao.class);
        if (dao.countByUsernameAndEmail(passwordResetRequestDTO) > 0) {
            // 임시비밀번호 8자리 생성 (문자, 기호, 숫자)
            String password = randomPw.getRandomPassword(8);
            // 임시비밀번호 암호화
            String encodedPw = bcrypt.encode(password);
            // 변경된 임시번호로 회원정보 업데이트
            dao.updatePassword2(passwordResetRequestDTO);
            // 메일로 임시비밀번호 전송
            resultCnt = mailSender.sendPw(passwordResetRequestDTO.getEmail(), password);
        }
        return resultCnt;
    }

    @Override
    public Member retrieveMemberById(Long memberId) {
        dao = template.getMapper(MemberDao.class);
        return dao.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 회원입니다.")
        );
    }

    @Override
    public int editMemberInfo(EditMember editMember) {
        int resultCnt = 0;
        try {
            dao = template.getMapper(MemberDao.class);
            resultCnt = dao.updateMember(editMember);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("회원정보 수정 실패");
        }
        return resultCnt;
    }

    @Override
    public int editMemberProfileImage(String path, MultipartFile imageFile) {
        return 0;
    }
    /* @Override
    public int editMember(EditMember editMember, HttpServletRequest req) throws IllegalStateException, IOException {
        int resultCnt = 0;
        File newFile = null;
        if (!(editMember.getPhoto() == null) && editMember.getPhoto().getSize() > 0) {
            String savePath = req.getSession().getServletContext().getRealPath(CommonsData.SAVE_URL);
            String[] files = editMember.getPhoto().getOriginalFilename().split("\\.");
            String exet = files[files.length - 1];
            String newFileName = System.nanoTime() + "." + exet;
            newFile = new File(savePath, newFileName);
            editMember.getPhoto().transferTo(newFile);
            editMember.setPhotoName(newFileName);
        } else {
            editMember.setPhotoName(editMember.getOldPhoto());
        }
        try {
            dao = template.getMapper(MemberDao.class);
            resultCnt = dao.editMember(editMember);
        } catch (Exception e) {
            // 파일이 저장된 후 DB관련 예외가 발생했을 때 : 저장했던 파일을 삭제
            if (newFile != null && newFile.exists()) {
                newFile.delete();
                e.printStackTrace();
                log.error("Member Modification failed");
            }
        }
        return resultCnt;
    }*/

    // 비밀번호 변경
    @Override
    public int changePassword(String username, String currentPassword, String newPassword) {
        int resultCnt = 0;
        String password = bcrypt.encode(newPassword);
        dao = template.getMapper(MemberDao.class);
        resultCnt = dao.changePassword(MemberPassword.builder()
                .username(username)
                .newPassword(password)
                .build());
        return resultCnt;
    }

    @Override
    public String checkEmail(String email) {
        dao = template.getMapper(MemberDao.class);
        return dao.countByEmail(email) > 0 ? "N" : "Y";
    }

    @Override
    public String checkId(String username) {
        dao = template.getMapper(MemberDao.class);
        return dao.countByUsername(username) > 0 ? "N" : "Y";
    }

    @Override
    public String checkPw(String username, String currentPw) {
        Member member = null;

        dao = template.getMapper(MemberDao.class);

        member = dao.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 회원입니다.")
        );

        if (!bcrypt.matches(currentPw, member.getPassword())) {
            return "N";
        }
        return "Y";

    }
}
