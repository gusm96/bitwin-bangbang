-- 회원
ALTER TABLE `bangbang`.`user`
	DROP PRIMARY KEY; -- 회원 기본키

-- 회원
DROP TABLE IF EXISTS `bangbang`.`user` RESTRICT;

-- 회원
CREATE TABLE `BANGBANG`.`USER` (
	`UIDX`     INT         NOT NULL COMMENT 'AI', -- 회원번호
	`USERID`   VARCHAR(25) NOT NULL UNIQUE,-- 아이디
	`PASSWORD` VARCHAR(250) NULL, -- 비밀번호
	`USERNAME` VARCHAR(25) NOT NULL , -- 이름(닉네임)
	`BIRTH`    DATE        NOT NULL, -- 생년월일
	`PHONENUM` VARCHAR(30) NOT NULL , -- 전화번호
	`EMAIL`    VARCHAR(40) NOT NULL UNIQUE, -- 이메일
	`ENOTIFY`  BOOLEAN     NOT NULL, -- 이메일수신
	`MNOTIFY`  BOOLEAN     NOT NULL, -- 문자수신
	`SNOTIFY`  BOOLEAN     NOT NULL, -- 카카오수신
	`PHOTO`    VARCHAR(250)       NULL, -- 프로필사진
	`DATE`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP-- 가입일
	
);
-- SNS BOOLEAN NOT NULL DEFAULT false -- 간편회원가입 여부
-- 회원
ALTER TABLE `bangbang`.`user`
	ADD CONSTRAINT `PK_user` -- 회원 기본키
		PRIMARY KEY (
			`uidx` -- 회원번호
		);

ALTER TABLE `bangbang`.`user`
	MODIFY COLUMN `uidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`user`
	AUTO_INCREMENT = 1;

----------------------------------------------------------------------------------------

-- 관리자
ALTER TABLE `bangbang`.`admin`
	DROP PRIMARY KEY; -- 관리자 기본키

-- 관리자
DROP TABLE IF EXISTS `bangbang`.`admin` RESTRICT;

-- 관리자
CREATE TABLE `bangbang`.`admin` (
	`aidx` INT         NOT NULL COMMENT 'AI', -- 관리자번호
	`aid`  VARCHAR(25) NOT NULL, -- 아이디
	`apw`  VARCHAR(25) NOT NULL  -- 비밀번호
);

-- 관리자
ALTER TABLE `bangbang`.`admin`
	ADD CONSTRAINT `PK_admin` -- 관리자 기본키
		PRIMARY KEY (
			`aidx` -- 관리자번호
		);

ALTER TABLE `bangbang`.`admin`
	MODIFY COLUMN `aidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`admin`
	AUTO_INCREMENT = 1;

----------------------------------------------------------------------------------------

-- 가맹점
ALTER TABLE `bangbang`.`store`
	DROP PRIMARY KEY; -- 가맹점 기본키

-- 가맹점
DROP TABLE IF EXISTS `bangbang`.`store` RESTRICT;

-- 가맹점
CREATE TABLE `BANGBANG`.`STORE` (
	`SIDX`    INT         NOT NULL COMMENT 'AI', -- 가맹점번호
    `STOREID` VARCHAR(25) NOT NULL UNIQUE,  -- 기맹점 아이디
    `STOREPW` VARCHAR(250) NOT NULL, -- 가맹점 비밀번호
	`SNAME`   VARCHAR(25) NOT NULL, -- 가맹점명
	`SPHONE`  VARCHAR(30) NOT NULL UNIQUE, -- 가맹점전화번호
	`ADDRESS` VARCHAR(50) NOT NULL, -- 가맹점주소
	`ONAME`   VARCHAR(25) NOT NULL, -- 사업자명
	`OPHONE`  VARCHAR(30) NOT NULL, -- 사업자전화번호
    `OEMAIL` VARCHAR(40) NOT NULL, -- 사업자이메일
	`REGNUM`  VARCHAR(30) NOT NULL UNIQUE, -- 사업자등록번호
	`PHOTO`   VARCHAR(250) NULL    , -- 프로필사진
	`DATE`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'SYSDATE' -- 등록일
);

-- 가맹점
ALTER TABLE `bangbang`.`store`
	ADD CONSTRAINT `PK_store` -- 가맹점 기본키
		PRIMARY KEY (
			`sidx` -- 가맹점번호
		);

ALTER TABLE `bangbang`.`store`
	MODIFY COLUMN `sidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`store`
	AUTO_INCREMENT = 1;
    
----------------------------------------------------------------------------------------
-- 가맹점 정보변경 신청
ALTER TABLE `bangbang`.`store_req`
	DROP FOREIGN KEY `FK_store_TO_store_req`; -- 가맹점 -> 가맹점 정보변경 신청

-- 가맹점 정보변경 신청
ALTER TABLE `bangbang`.`store_req`
	DROP PRIMARY KEY; -- 가맹점 정보변경 신청 기본키

-- 가맹점 정보변경 신청
DROP TABLE IF EXISTS `bangbang`.`store_req` RESTRICT;

-- 가맹점 정보변경 신청
CREATE TABLE `bangbang`.`store_req` (
	`sridx`   INT          NOT NULL COMMENT 'AI', -- 새 컬럼
	`sname`   VARCHAR(50)  NOT NULL, -- 가맹점명
	`sphone`  VARCHAR(25)  NOT NULL, -- 가맹점번호
	`address` VARCHAR(250) NOT NULL, -- 가맹점주소
	`sidx`    INT          NOT NULL  -- 가맹점인덱스
);

-- 가맹점 정보변경 신청
ALTER TABLE `bangbang`.`store_req`
	ADD CONSTRAINT `PK_store_req` -- 가맹점 정보변경 신청 기본키
		PRIMARY KEY (
			`sridx` -- 새 컬럼
		);

-- 가맹점 정보변경 신청
ALTER TABLE `bangbang`.`store_req`
	ADD CONSTRAINT `FK_store_TO_store_req` -- 가맹점 -> 가맹점 정보변경 신청
		FOREIGN KEY (
			`sidx` -- 가맹점인덱스
		)
		REFERENCES `bangbang`.`store` ( -- 가맹점
			`sidx` -- 가맹점인덱스
		);
ALTER TABLE `bangbang`.`store_req`
	MODIFY COLUMN `sridx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`store_req`
	AUTO_INCREMENT = 1;
----------------------------------------------------------------------------------------

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	DROP FOREIGN KEY `FK_user_TO_usercoupon`; -- 회원 -> 발급쿠폰

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	DROP FOREIGN KEY `FK_coupons_TO_usercoupon`; -- 쿠폰 -> 발급쿠폰

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	DROP PRIMARY KEY; -- 발급쿠폰 기본키

-- 발급쿠폰
DROP TABLE IF EXISTS `bangbang`.`usercoupon` RESTRICT;

-- 발급쿠폰
CREATE TABLE `bangbang`.`usercoupon` (
	`ucidx`     INT       NOT NULL COMMENT 'AI', -- 쿠폰발급번호
	`uidx`      INT       NOT NULL, -- 회원번호
	`coidx`     INT       NOT NULL, -- 쿠폰번호
	`pubdate`   TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT 'sysdate', -- 쿠폰발급일
	`exdate`    TIMESTAMP NOT NULL, -- 쿠폰소멸일
	`available` BOOLEAN   NOT NULL DEFAULT 1 COMMENT 'default t' -- 사용가능여부
);

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	ADD CONSTRAINT `PK_usercoupon` -- 발급쿠폰 기본키
		PRIMARY KEY (
			`ucidx` -- 쿠폰발급번호
		);

ALTER TABLE `bangbang`.`usercoupon`
	MODIFY COLUMN `ucidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`usercoupon`
	AUTO_INCREMENT = 1;

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	ADD CONSTRAINT `FK_user_TO_usercoupon` -- 회원 -> 발급쿠폰
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `MY_SCHEMA`.`user` ( -- 회원
			`uidx` -- 회원번호
		);

-- 발급쿠폰
ALTER TABLE `bangbang`.`usercoupon`
	ADD CONSTRAINT `FK_coupons_TO_usercoupon` -- 쿠폰 -> 발급쿠폰
		FOREIGN KEY (
			`coidx` -- 쿠폰번호
		)
		REFERENCES `MY_SCHEMA`.`coupons` ( -- 쿠폰
			`coidx` -- 쿠폰번호
		);

----------------------------------------------------------------------------------------

-- 쿠폰
ALTER TABLE `bangbang`.`coupons`
	DROP PRIMARY KEY; -- 쿠폰 기본키

-- 쿠폰
DROP TABLE IF EXISTS `bangbang`.`coupons` RESTRICT;

-- 쿠폰
CREATE TABLE `bangbang`.`coupons` (
	`coidx`    INT         NOT NULL COMMENT 'AI', -- 쿠폰번호
	`name`     VARCHAR(25) NOT NULL, -- 쿠폰이름
	`discount` TINYINT     NOT NULL, -- 쿠폰할인률
	`expiry`   SMALLINT    NOT NULL  -- 유효기간
);

-- 쿠폰
ALTER TABLE `bangbang`.`coupons`
	ADD CONSTRAINT `PK_coupons` -- 쿠폰 기본키
		PRIMARY KEY (
			`coidx` -- 쿠폰번호
		);

ALTER TABLE `bangbang`.`coupons`
	MODIFY COLUMN `coidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`coupons`
	AUTO_INCREMENT = 1;
    
-------------------------------------------------------------------------------------

-- 공지게시물
ALTER TABLE `bangbang`.`notice_board`
	DROP PRIMARY KEY; -- 공지게시물 기본키

-- 공지게시물
DROP TABLE IF EXISTS `bangbang`.`notice_board` RESTRICT;

-- 공지게시물
CREATE TABLE `bangbang`.`notice_board` (
	`nidx`       INT          NOT NULL COMMENT 'AI', -- 게시글번호
	`title`      VARCHAR(100) NOT NULL, -- 제목
	`content`    TEXT         NOT NULL, -- 내용
	`regdate`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
	 COMMENT 'sysdate', -- 등록날짜
	`updatedate` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sysdate' -- 수정날짜
);

-- 공지게시물
ALTER TABLE `bangbang`.`notice_board`
	ADD CONSTRAINT `PK_notice_board` -- 공지게시물 기본키
		PRIMARY KEY (
			`nidx` -- 게시글번호
		);

ALTER TABLE `bangbang`.`notice_board`
	MODIFY COLUMN `nidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`notice_board`
	AUTO_INCREMENT = 1;

-------------------------------------------------------------------------------------

-- FAQ
ALTER TABLE `bangbang`.`faq`
	DROP PRIMARY KEY; -- FAQ 기본키

-- FAQ
DROP TABLE IF EXISTS `bangbang`.`faq` RESTRICT;

-- FAQ
CREATE TABLE `bangbang`.`faq` (
	`fqidx`      INT          NOT NULL COMMENT 'AI', -- FAQ 번호
	`title`      VARCHAR(100) NOT NULL, -- 제목
	`content`   TEXT         NOT NULL, -- 질문 내용
	`regdate`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sysdate', -- 등록 날짜
	`updatedate` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sysdate' -- 수정 날짜
);

-- FAQ
ALTER TABLE `bangbang`.`faq`
	ADD CONSTRAINT `PK_faq` -- FAQ 기본키
		PRIMARY KEY (
			`fqidx` -- FAQ 번호
		);

ALTER TABLE `bangbang`.`faq`
	MODIFY COLUMN `fqidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`faq`
	AUTO_INCREMENT = 1;

-------------------------------------------------------------------------------------

-- 1:1 문의
ALTER TABLE `bangbang`.`paq`
	DROP FOREIGN KEY `FK_user_TO_paq`; -- 회원 -> 1:1 문의 질문

-- 1:1 문의
ALTER TABLE `bangbang`.`paq`
	DROP PRIMARY KEY; -- 1:1 문의 질문 기본키

-- 1:1 문의
DROP TABLE IF EXISTS `bangbang`.`paq` RESTRICT;

-- 1:1 문의
CREATE TABLE `bangbang`.`paq` (
	`pqidx`      INT          NOT NULL COMMENT 'AI', -- 질문 번호
	`uidx`       INT          NOT NULL, -- 회원번호
	`title`      VARCHAR(100) NOT NULL, -- 제목
	`content`    TEXT         NOT NULL, -- 내용
	`reply`      TEXT 		  NULL, -- 답변
	`regdate`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sysdate', -- 등록 날짜
	`updatedate` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'sysdate' -- 수정 날짜
);

-- 1:1 문의
ALTER TABLE `bangbang`.`paq`
	ADD CONSTRAINT `PK_paq` -- 1:1 문의 질문 기본키
		PRIMARY KEY (
			`pqidx` -- 질문 번호
		);

ALTER TABLE `bangbang`.`paq`
	MODIFY COLUMN `pqidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`paq`
	AUTO_INCREMENT = 1;

-- 1:1 문의
ALTER TABLE `bangbang`.`paq`
	ADD CONSTRAINT `FK_user_TO_paq` -- 회원 -> 1:1 문의 질문
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `bangbang`.`user` ( -- 회원
			`uidx` -- 회원번호
		);
-------------------------------------------------------------------------------------
-- 상품
ALTER TABLE `bangbang`.`item`
	DROP PRIMARY KEY; -- 상품 기본키

DROP TABLE IF EXISTS `bangbang`.`item` RESTRICT;

CREATE TABLE `bangbang`.`item` (
	`iidx`     INT         NOT NULL COMMENT 'AI', -- 상품번호
	`name`     VARCHAR(25) NOT NULL, -- 상품명
	`ename`    VARCHAR(50) NOT NULL, -- 영문명
	`ml`       SMALLINT    NOT NULL, -- 용량
	`proof`    TINYINT     NOT NULL, -- 도수
	`location` VARCHAR(20) NOT NULL, -- 지역
	`type`     VARCHAR(20) NOT NULL, -- 종류
	`taste`    VARCHAR(20) NOT NULL, -- 맛
	`scent`    VARCHAR(20) NOT NULL, -- 향
	`oprice`   MEDIUMINT   NOT NULL, -- 원가
	`sprice`   MEDIUMINT   NOT NULL, -- 판매가
	`discount` TINYINT     NULL,     -- 할인률
	`qty`      SMALLINT    NOT NULL, -- 재고
	`etc`      VARCHAR(30) NULL      -- 추가정보
);

ALTER TABLE `bangbang`.`item`
	ADD CONSTRAINT `PK_item` -- 상품 기본키
		PRIMARY KEY (
			`iidx` -- 상품번호
		);

     ALTER TABLE `bangbang`.`item`
	MODIFY COLUMN `iidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';
    
    ALTER TABLE `bangbang`.`item`
	AUTO_INCREMENT = 1;

-- 찜목록

	ALTER TABLE `bangbang`.`wishlist`
	DROP PRIMARY KEY; -- 찜목록 기본키
    
    DROP TABLE IF EXISTS `bangbang`.`wishlist` RESTRICT;

    CREATE TABLE `bangbang`.`wishlist` (
	`widx` INT NOT NULL COMMENT 'AI', -- 찜번호
	`uidx` INT NOT NULL, -- 회원번호
	`iidx` INT NOT NULL  -- 상품번호
);
    
    ALTER TABLE `bangbang`.`wishlist`
	ADD CONSTRAINT `PK_wishlist` -- 찜목록 기본키
		PRIMARY KEY (
			`widx` -- 찜번호
		);

ALTER TABLE `bangbang`.`wishlist`
	MODIFY COLUMN `widx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`wishlist`
	AUTO_INCREMENT = 1;
    
    ALTER TABLE `bangbang`.`wishlist`
	ADD CONSTRAINT `FK_user_TO_wishlist` -- 회원 -> 찜목록
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `bangbang`.`user` ( -- 회원
			`uidx` -- 회원번호
		);

ALTER TABLE `bangbang`.`wishlist`
	ADD CONSTRAINT `FK_item_TO_wishlist` -- 상품 -> 찜목록
		FOREIGN KEY (
			`iidx` -- 상품번호
		)
		REFERENCES `bangbang`.`item` ( -- 상품
			`iidx` -- 상품번호
		);

-- 리뷰

ALTER TABLE `bangbang`.`review`
	DROP PRIMARY KEY; -- 리뷰 기본키

-- 리뷰
DROP TABLE IF EXISTS `bangbang`.`review` RESTRICT;


    CREATE TABLE `bangbang`.`review` (
	`ridx`    INT       NOT NULL COMMENT 'AI', -- 리뷰번호
	`uidx`    INT       NOT NULL, -- 회원번호
	`iidx`    INT       NOT NULL, -- 상품번호
	`content` TEXT      NOT NULL, -- 내용
	`rating`  TINYINT   NOT NULL, -- 평점
	`date`    TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT 'sysdate' -- 작성일
);
    
    ALTER TABLE `bangbang`.`review`
	ADD CONSTRAINT `PK_review` -- 리뷰 기본키
		PRIMARY KEY (
			`ridx` -- 리뷰번호
		);

ALTER TABLE `bangbang`.`review`
	MODIFY COLUMN `ridx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`review`
	AUTO_INCREMENT = 1;

ALTER TABLE `bangbang`.`review`
	ADD CONSTRAINT `FK_user_TO_review` -- 회원 -> 리뷰
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `bangbang`.`user` ( -- 회원
			`uidx` -- 회원번호
		);

ALTER TABLE `bangbang`.`review`
	ADD CONSTRAINT `FK_item_TO_review` -- 상품 -> 리뷰
		FOREIGN KEY (
			`iidx` -- 상품번호
		)
		REFERENCES `bangbang`.`item` ( -- 상품
			`iidx` -- 상품번호
		);
    
-- 상품게시글

ALTER TABLE `bangbang`.`sales_board`
	DROP PRIMARY KEY; -- 상품게시글 기본키

-- 상품게시글
DROP TABLE IF EXISTS `bangbang`.`sales_board` RESTRICT;

    CREATE TABLE `bangbang`.`sales_board` (
	`iidx`    INT          NOT NULL COMMENT 'AI', -- 상품번호
	`title`   VARCHAR(100) NOT NULL, -- 제목
	`content` TEXT         NOT NULL, -- 내용
	`date`    TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT 'sysdate', -- 작성일
	`hits`    INT          NOT NULL DEFAULT 0 COMMENT 'default 0', -- 조회수
	`thumb`   BLOB         NOT NULL, -- 썸네일
	`photo1`  BLOB         NOT NULL, -- 대표사진1
	`photo2`  BLOB         NULL      -- 대표사진2
);
    
    ALTER TABLE `bangbang`.`sales_board`
	ADD CONSTRAINT `PK_sales_board` -- 상품게시글 기본키
		PRIMARY KEY (
			`iidx` -- 상품번호
		);

ALTER TABLE `bangbang`.`sales_board`
	MODIFY COLUMN `iidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`sales_board`
	AUTO_INCREMENT = 1;

ALTER TABLE `bangbang`.`sales_board`
	ADD CONSTRAINT `FK_item_TO_sales_board` -- 상품 -> 상품게시글
		FOREIGN KEY (
			`iidx` -- 상품번호
		)
		REFERENCES `bangbang`.`item` ( -- 상품
			`iidx` -- 상품번호
		);
-----------------------------------------------------------------------------------------------------
-- 주문
ALTER TABLE `bangbang`.`order`
	DROP FOREIGN KEY `FK_user_TO_order`; -- 회원 -> 주문

-- 주문
ALTER TABLE `bangbang`.`order`
	DROP FOREIGN KEY `FK_store_TO_order`; -- 가맹점 -> 주문

-- 주문
ALTER TABLE `bangbang`.`order`
	DROP PRIMARY KEY; -- 주문 기본키

-- 주문
DROP TABLE IF EXISTS `bangbang`.`order` RESTRICT;

-- 주문
CREATE TABLE `bangbang`.`order` (
	`oidx`   INT         NOT NULL COMMENT 'AI', -- 주문번호
	`uidx`   INT         NOT NULL, -- 회원번호
	`sidx`   INT         NOT NULL, -- 가맹점번호
	`name`   VARCHAR(25) NOT NULL, -- 주문자명
	`phone`  VARCHAR(30) NOT NULL, -- 연락처
	`msg`    VARCHAR(30) NULL,     -- 배송메세지
	`date`   TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT 'sysdate', -- 주문일시
	`status` VARCHAR(20) NOT NULL DEFAULT '주문완료' COMMENT 'default' -- 주문현황
);

-- 주문
ALTER TABLE `bangbang`.`order`
	ADD CONSTRAINT `PK_order` -- 주문 기본키
		PRIMARY KEY (
			`oidx` -- 주문번호
		);

ALTER TABLE `bangbang`.`order`
	MODIFY COLUMN `oidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`order`
	AUTO_INCREMENT = 1;

-- 주문
ALTER TABLE `bangbang`.`order`
	ADD CONSTRAINT `FK_user_TO_order` -- 회원 -> 주문
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `bangbang`.`user` ( -- 회원
			`uidx` -- 회원번호
		);

-- 주문
ALTER TABLE `bangbang`.`order`
	ADD CONSTRAINT `FK_store_TO_order` -- 가맹점 -> 주문
		FOREIGN KEY (
			`sidx` -- 가맹점번호
		)
		REFERENCES `bangbang`.`store` ( -- 가맹점
			`sidx` -- 가맹점번호
		);

--------------------------------------------------------------------------------------

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	DROP FOREIGN KEY `FK_order_TO_order_item`; -- 주문 -> 주문상품

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	DROP FOREIGN KEY `FK_item_TO_order_item`; -- 상품 -> 주문상품

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	DROP PRIMARY KEY; -- 주문상품 기본키

-- 주문상품
DROP TABLE IF EXISTS `bangbang`.`order_item` RESTRICT;

-- 주문상품
CREATE TABLE `bangbang`.`order_item` (
	`oiidx` INT     NOT NULL COMMENT 'AI', -- 장바구니번호
	`oidx`  INT     NOT NULL, -- 주문번호
	`iidx`  INT     NOT NULL, -- 상품번호
	`qty`   TINYINT NOT NULL -- 상품개수
);

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	ADD CONSTRAINT `PK_order_item` -- 주문상품 기본키
		PRIMARY KEY (
			`oiidx` -- 장바구니번호
		);

ALTER TABLE `bangbang`.`order_item`
	MODIFY COLUMN `oiidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`order_item`
	AUTO_INCREMENT = 1;

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	ADD CONSTRAINT `FK_order_TO_order_item` -- 주문 -> 주문상품
		FOREIGN KEY (
			`oidx` -- 주문번호
		)
		REFERENCES `bangbang`.`order` ( -- 주문
			`oidx` -- 주문번호
		);

-- 주문상품
ALTER TABLE `bangbang`.`order_item`
	ADD CONSTRAINT `FK_item_TO_order_item` -- 상품 -> 주문상품
		FOREIGN KEY (
			`iidx` -- 상품번호
		)
		REFERENCES `bangbang`.`item` ( -- 상품
			`iidx` -- 상품번호
		);

--------------------------------------------------------------------------------------

-- 배송
ALTER TABLE `bangbang`.`delivery`
	DROP FOREIGN KEY `FK_order_TO_delivery`; -- 주문 -> 배송

-- 배송
ALTER TABLE `bangbang`.`delivery`
	DROP PRIMARY KEY; -- 배송 기본키

-- 배송
DROP TABLE IF EXISTS `bangbang`.`delivery` RESTRICT;

-- 배송
CREATE TABLE `bangbang`.`delivery` (
	`didx`     INT         NOT NULL COMMENT 'AI', -- 배송번호
	`oidx`     INT         NOT NULL, -- 주문번호
	`service`  VARCHAR(20) NOT NULL, -- 배송수단
	`provider` VARCHAR(25) NOT NULL, -- 업체명
	`no`       VARCHAR(30) NOT NULL, -- 송장및접수번호
	`fee`      SMALLINT    UNSIGNED NOT NULL  -- 운송료
);

-- 배송
ALTER TABLE `bangbang`.`delivery`
	ADD CONSTRAINT `PK_delivery` -- 배송 기본키
		PRIMARY KEY (
			`didx` -- 배송번호
		);

ALTER TABLE `bangbang`.`delivery`
	MODIFY COLUMN `didx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`delivery`
	AUTO_INCREMENT = 1;

-- 배송
ALTER TABLE `bangbang`.`delivery`
	ADD CONSTRAINT `FK_order_TO_delivery` -- 주문 -> 배송
		FOREIGN KEY (
			`oidx` -- 주문번호
		)
		REFERENCES `bangbang`.`order` ( -- 주문
			`oidx` -- 주문번호
		);

--------------------------------------------------------------------------------------

-- 결제
ALTER TABLE `bangbang`.`payment`
	DROP FOREIGN KEY `FK_order_TO_payment`; -- 주문 -> 결제

-- 결제
ALTER TABLE `bangbang`.`payment`
	DROP FOREIGN KEY `FK_usercoupon_TO_payment`; -- 발급쿠폰 -> 결제

-- 결제
ALTER TABLE `bangbang`.`payment`
	DROP PRIMARY KEY; -- 결제 기본키

-- 결제
DROP TABLE IF EXISTS `bangbang`.`payment` RESTRICT;

-- 결제
CREATE TABLE `bangbang`.`payment` (
	`pidx`       INT         NOT NULL COMMENT 'AI', -- 결제번호
	`oidx`       INT         NOT NULL, -- 주문번호
	`totalprice` MEDIUMINT   UNSIGNED NOT NULL, -- 총상품금액
	`pointused`  MEDIUMINT   NULL,     -- 사용적립금
	`totalpay`   MEDIUMINT   UNSIGNED NOT NULL, -- 결제금액
	`method`     VARCHAR(20) NOT NULL, -- 결제수단
	`status`     VARCHAR(20) NOT NULL DEFAULT '결제완료' COMMENT 'default', -- 결제상태
	`ucidx`      INT         NULL      -- 쿠폰발급번호
);

-- 결제
ALTER TABLE `bangbang`.`payment`
	ADD CONSTRAINT `PK_payment` -- 결제 기본키
		PRIMARY KEY (
			`pidx` -- 결제번호
		);

ALTER TABLE `bangbang`.`payment`
	MODIFY COLUMN `pidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`payment`
	AUTO_INCREMENT = 1;

-- 결제
ALTER TABLE `bangbang`.`payment`
	ADD CONSTRAINT `FK_order_TO_payment` -- 주문 -> 결제
		FOREIGN KEY (
			`oidx` -- 주문번호
		)
		REFERENCES `bangbang`.`order` ( -- 주문
			`oidx` -- 주문번호
		);

-- 결제
ALTER TABLE `bangbang`.`payment`
	ADD CONSTRAINT `FK_usercoupon_TO_payment` -- 발급쿠폰 -> 결제
		FOREIGN KEY (
			`ucidx` -- 쿠폰발급번호
		)
		REFERENCES `bangbang`.`usercoupon` ( -- 발급쿠폰
			`ucidx` -- 쿠폰발급번호
		);
        
ALTER TABLE `bangbang`.`payment` 
ADD COLUMN `discount` MEDIUMINT NULL DEFAULT 0 AFTER `totalprice`;

--------------------------------------------------------------------------------------

-- 적립
ALTER TABLE `bangbang`.`point`
	DROP FOREIGN KEY `FK_user_TO_point`; -- 회원 -> 적립

-- 적립
ALTER TABLE `bangbang`.`point`
	DROP FOREIGN KEY `FK_payment_TO_point`; -- 결제 -> 적립

-- 적립
ALTER TABLE `bangbang`.`point`
	DROP PRIMARY KEY; -- 적립 기본키

-- 적립
DROP TABLE IF EXISTS `bangbang`.`point` RESTRICT;

-- 적립
CREATE TABLE `bangbang`.`point` (
	`poidx`   INT       NOT NULL COMMENT 'AI', -- 적립번호
	`uidx`    INT       NOT NULL, -- 회원번호
	`pidx`    INT       NOT NULL, -- 결제번호
	`changes`  MEDIUMINT NOT NULL COMMENT '+/-', -- 거래적립금
	`balance` MEDIUMINT UNSIGNED NOT NULL, -- 잔여적립금
	`date`    TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT 'sysdate' -- 적용일시
);

-- 적립
ALTER TABLE `bangbang`.`point`
	ADD CONSTRAINT `PK_point` -- 적립 기본키
		PRIMARY KEY (
			`poidx` -- 적립번호
		);

ALTER TABLE `bangbang`.`point`
	MODIFY COLUMN `poidx` INT NOT NULL AUTO_INCREMENT COMMENT 'AI';

ALTER TABLE `bangbang`.`point`
	AUTO_INCREMENT = 1;

-- 적립
ALTER TABLE `bangbang`.`point`
	ADD CONSTRAINT `FK_user_TO_point` -- 회원 -> 적립
		FOREIGN KEY (
			`uidx` -- 회원번호
		)
		REFERENCES `bangbang`.`user` ( -- 회원
			`uidx` -- 회원번호
		);

-- 적립
ALTER TABLE `bangbang`.`point`
	ADD CONSTRAINT `FK_payment_TO_point` -- 결제 -> 적립
		FOREIGN KEY (
			`pidx` -- 결제번호
		)
		REFERENCES `bangbang`.`payment` ( -- 결제
			`pidx` -- 결제번호
		);