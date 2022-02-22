package com.bitwin.bangbang.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.member.service.MailSenderService;
import com.bitwin.bangbang.member.service.RamdomPassword;
import com.bitwin.bangbang.store.dao.StoreDao;
import com.bitwin.bangbang.store.domain.Store;

@Service
public class AdminStoreService {
	private StoreDao dao;

	@Autowired
	private SqlSessionTemplate template;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private MailSenderService mailSender;

	@Autowired
	private RamdomPassword randomPw;

	public int insertStore(Store store) {
		int resultCnt = 0;

		// 임시 비밀번호 생성
		String pw = randomPw.getRamdomPassword(8);

		// 임시 비밀번호를 암호화 하여 DB에 저장
		String bPw = bcrypt.encode(pw);

		store.setStorePw(bPw);

		dao = template.getMapper(StoreDao.class);

		resultCnt = dao.insertStore(store);

		if (resultCnt > 0) {
			// 가맹점 등록 성공
			// 사업자의 이메일로 가맹점 등록 정보를 전송
			if (mailSender.sendStore(store) > 0) {
				System.out.println("메일 발송 성공");
			} else {
				System.out.println("메일 발송 실패");
			}
		} else {
			System.out.println("가맹점 등록 실패");
		}

		return resultCnt;
	}

	public List<Store> selectAll() {
		List<Store> store = null;

		dao = template.getMapper(StoreDao.class);

		store = dao.selectAll();

		return store;
	}

}
