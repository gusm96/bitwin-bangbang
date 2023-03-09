package com.bitwin.bangbang.member.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.dao.MemberDao;
import com.bitwin.bangbang.member.domain.EditMember;
import com.bitwin.bangbang.member.domain.Member;
import com.bitwin.bangbang.member.domain.MemberPassword;
import com.bitwin.bangbang.member.domain.MemberRegRequest;
import com.bitwin.bangbang.member.domain.SearchPassword;
import com.bitwin.bangbang.member.domain.SimpleRegRequest;

@Service
public class MemberService {

	private MemberDao dao;

	@Autowired
	private SqlSessionTemplate template;

	@Autowired
	private RamdomPassword ramdomPw;
	@Autowired
	private MailSenderService mailSender;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	// 회원가입
	public int insertMember(MemberRegRequest regRequest) {
		int resultCnt = 0;

		regRequest.setPhotoName("default.jpg");

		// 비밀번호 암호화 필요
		String bPw = bcrypt.encode(regRequest.getPassword());
		// 암호화 된 비밀번호를 저장
		regRequest.setPassword(bPw);

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.insertMember(regRequest);

		if (mailSender.send(regRequest.getEmail(), regRequest.getUsername()) > 0) {
			System.out.println("메일전송 성공");
		} else {
			System.out.println("메일전송 실패");
		}

		return resultCnt;
	}

	// 간편 회원가입
	public int insertSimpleMember(SimpleRegRequest regRequest, HttpServletRequest req) {
		int resultCnt = 0;

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.insertSimpleMember(regRequest);
		if (mailSender.send(regRequest.getEmail(), regRequest.getUsername()) > 0) {
			System.out.println("메일전송 성공");
		} else {
			System.out.println("메일전송 실패");
		}

		return resultCnt;
	}

	public int searchById(String email) {
		int resultCnt = 0;

		dao = template.getMapper(MemberDao.class);

		if (dao.selectCountByEmail(email) > 0) {
			// 메일로 회원의 아이디 전송
			String userid = dao.searchId(email);
			resultCnt = mailSender.sendId(email, userid);
		}

		return resultCnt;
	}

	public int searchByPw(SearchPassword searchPw) {
		int resultCnt = 0;

		dao = template.getMapper(MemberDao.class);

		if (dao.selectCountByEmailUserId(searchPw) > 0) {
			// 임시비밀번호 8자리 생성 (문자, 기호, 숫자)
			String password = ramdomPw.getRamdomPassword(8);
			// 임시비밀번호 암호화
			searchPw.setBpw(bcrypt.encode(password));
			// 변경된 임시번호로 회원정보 업데이트
			dao.updatePassword2(searchPw);
			// 메일로 임시비밀번호 전송
			resultCnt = mailSender.sendPw(searchPw.getEmail(), password);
		}

		return resultCnt;
	}

	public Member getMember(int uidx) {
		dao = template.getMapper(MemberDao.class);
		return dao.selectByIdx(uidx);
	}

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
			System.out.println("성공");
		} catch (Exception e) {
			// 파일이 저장된 후 DB관련 예외가 발생했을 때 : 저장했던 파일을 삭제
			if (newFile != null && newFile.exists()) {
				newFile.delete();
				System.out.println(e);
				System.out.println("실패");
			}
		}

		return resultCnt;
	}

	// 비밀번호 변경
	public int changePw(String userid, String newPw) {
		int resultCnt = 0;
		MemberPassword memberPw = new MemberPassword();

		String password = bcrypt.encode(newPw);

		memberPw.setUserid(userid);
		memberPw.setNewPassword(password);

		dao = template.getMapper(MemberDao.class);

		resultCnt = dao.updatePassword(memberPw);

		return resultCnt;
	}
}
