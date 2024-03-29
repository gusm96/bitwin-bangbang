package com.bitwin.bangbang.admin.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitwin.bangbang.admin.domain.StoreEditReqListView;
import com.bitwin.bangbang.admin.domain.StoreListView;
import com.bitwin.bangbang.member.service.MailSenderService;
import com.bitwin.bangbang.member.service.RandomPassword;
import com.bitwin.bangbang.store.dao.StoreDao;
import com.bitwin.bangbang.store.domain.Store;
import com.bitwin.bangbang.store.domain.StoreEditRequest;
import com.bitwin.bangbang.store.domain.StoreEditRequestList;
import com.bitwin.bangbang.store.domain.StoreRegRequest;

@Service
@RequiredArgsConstructor
public class AdminStoreService {
	private StoreDao dao;

	private final SqlSessionTemplate template;

	private final BCryptPasswordEncoder bcrypt;

	private final MailSenderService mailSender;

	private final RandomPassword randomPw;

	// 페이지 당 표현할 가맹점 수
	private final int COUNT_PER_PAGE = 15;

	// 페이징 번호 노출 개수
	private final int COUNT_PER_PAGING_NUM = 5;

	public int insertStore(StoreRegRequest regRequest) {
		int resultCnt = 0;

		// 임시 비밀번호 생성
		String pw = randomPw.getRandomPassword(8);

		// 임시 비밀번호를 암호화 하여 DB에 저장
		String bPw = bcrypt.encode(pw);
		regRequest.setPhoto("storedefault.jpg");
		regRequest.setStorePw(bPw);

		dao = template.getMapper(StoreDao.class);

		resultCnt = dao.insertStore(regRequest);

		if (resultCnt > 0) {
			// 가맹점 등록 성공
			// 사업자의 이메일로 가맹점 등록 정보를 전송
			if (mailSender.sendStore(regRequest, pw) > 0) {
				System.out.println("메일 발송 성공");
			} else {
				System.out.println("메일 발송 실패");
			}
		} else {
			System.out.println("가맹점 등록 실패");
		}

		return resultCnt;
	}

	// 가맹점 정보 리스트
	public StoreListView getStoreList(int currentPage) {
		List<Store> list = null;

		dao = template.getMapper(StoreDao.class);

		int totalCount = dao.selectTotalCount();

		int index = (currentPage - 1) * COUNT_PER_PAGE;

		list = dao.selectAll(index, COUNT_PER_PAGE);

		return new StoreListView(currentPage, COUNT_PER_PAGE, COUNT_PER_PAGING_NUM, list, totalCount);
	}

	// 가맹점 아이디 중복 체크
	public String checkStoreId(String storeId) {
		String result = "";

		dao = template.getMapper(StoreDao.class);

		int count = dao.selectByStoreId(storeId);

		if (count > 0) {
			result = "N";
		} else {
			result = "Y";
		}
		return result;

	}

	public Store storeDetial(String sname) {
		Store store = null;

		dao = template.getMapper(StoreDao.class);

		store = dao.selectBySname(sname);

		return store;
	}

	public StoreEditReqListView getEditReqList(int currentPage) {

		List<StoreEditRequestList> list = null;

		dao = template.getMapper(StoreDao.class);

		int totalCount = dao.editReqTotalCount();

		int index = (currentPage - 1) * COUNT_PER_PAGE;

		list = dao.selectEditRequestList(index, COUNT_PER_PAGE);

		return new StoreEditReqListView(currentPage, COUNT_PER_PAGE, COUNT_PER_PAGING_NUM, list, totalCount);
	}

	public StoreEditRequest getEditRequest(int sridx) {

		StoreEditRequest storeReq = null;

		dao = template.getMapper(StoreDao.class);

		storeReq = dao.selectStoreEditReq(sridx);

		storeReq.setCurInfo(dao.selectStore(storeReq.getSidx()));

		return storeReq;
	}

	public int acceptEditRequest(StoreEditRequest editRequest) {
		int resultCnt = 0;

		dao = template.getMapper(StoreDao.class);

		resultCnt = dao.updateStore(editRequest);

		if (resultCnt > 0) {
			// 가맹점 정보 수정이 완료되었음을 알리는 메일 전송
			resultCnt = mailSender.sendStoreReq(editRequest.getOemail());
			if (resultCnt > 0) {
				// 성공적으로 update 되었으므로 요청 리스트 에서 삭제
				dao.deleteEditReq(editRequest.getSidx());
			}
		}

		return resultCnt;
	}
	// 가맹점 정보 수정 요청 전체 수
	public int editReqCount() {
		int resultCnt = 0;

		dao = template.getMapper(StoreDao.class);

		resultCnt = dao.editReqTotalCount();

		return resultCnt;
	}
	
	// 가맹점 정보 수정 요청 거절 
	public String cancleEditReq(String sridx, String text) {
		String result = "";

		StoreEditRequest editRequest = new StoreEditRequest();

		dao = template.getMapper(StoreDao.class);
		
		editRequest = dao.selectStoreEditReq(Integer.parseInt(sridx));

		result = mailSender.sendCancle(editRequest.getOemail(), text);

		if (result.equals("Y")) {
			dao.deleteEditReq(editRequest.getSidx());
		}
		return result;
	}

}
