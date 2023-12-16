# 방방술래_project

------

### 👨🏻‍💻About Project.

> “전국 방방곡곡 원하는 술이 찾아온다.” 
>
> ”방방술래”는 소비자들이 주류를 구매할 때 겪는 어려움을 해소하기 위한 주류 픽업 서비스 플랫폼 입니다.

<img src=".\images\image1.png" style="zoom:50%;" />

- 프로젝트 진행 일정

  | 일자                    | 내용                                                         |
  | ----------------------- | ------------------------------------------------------------ |
  | 2022.01.17 ~ 2022.01.21 | 주제 설정, 기능 정의, 업무 분담                              |
  | 2022.01.21              | 주제발표                                                     |
  | 2022.01.24~2022.01.28   | 데이터베이스 설계, 플로우 차트 작성 → [link](https://docs.google.com/spreadsheets/d/1X8fWYxPlJHykNt62tOavay0s0QIRBxUxNkZfTeoUh4I/edit#gid=488482738) |
  | 2022.02.12              | 중간 점검                                                    |
  | 2022.02.25              | 개발 완료                                                    |
  | 2022.02.28              | PPT 준비                                                     |
  | 2022.03.02              | 발표 및 시연                                                 |
  | 2022.03.02~             | 개별로 리팩토링 및 기능 추가                                 |

  ------

- 4명의 팀원 ( 백엔드와 프론트엔드 구분없이 각자 맡은 기능을 구현하였습니다. )

|          [구성모](https://github.com/gusm96)           |      [김현우](https://github.com/wmrwmr)      | [문가란](https://github.com/lililights) |  [신승민](https://github.com/siner44)   |
| :----------------------------------------------------: | :-------------------------------------------: | :-------------------------------------: | :-------------------------------------: |
| - 일반 회원 Part<br />- 가맹점 Part<br />- 관리자 Part | - 공지사항 게시글 Part<br />- 고객서비스 Part |   - 결제 Part<br />- 가맹점 지도 Part   | - 상품등록 Part<br />- 상품 게시글 Part |

------
### ERD

<img width="758" alt="방방ERD" src="https://github.com/gusm96/bitwin-bangbang/assets/77833389/c523bd2d-b0bb-4cbd-b83c-181eefe8fba8">
------
### 🛠️Using Technology

- BackEnd
  - JAVA, Spring Framework, MyBatis
- FrontEnd
  - Javascript, JSP, HTML5, CCS3, jQuery, Ajax, Bootstrap
- Database
  - MySQL
- Tools
  - Eclipse, STS3, Git

------

### 🙋🏻‍♂️My Part

- 회원 및 가맹점 관리
  - 로그인/가입 기능 구현
    - Session을 이용한 회원 로그인 상태 관리
  - 마이페이지 구현
    - 정보 수정 및 회원 탈퇴 / 가맹점 해지
  - Kakao / Naver REST API를 활용한 간편 로그인 및 회원가입 기능 구현
  - 아이디 / 비밀번호 찾기 기능 구현
    - javax.mail 사용 - 사용자 메일로 아이디 / 임시비밀번호 전송
- 관리자 페이지 구축
  - 관리자 페이지 로그인 / 로그아웃 기능 구현
  - 회원 / 가맹점 조회 및 관리 기능 구현
  - Session에 관리자 권한 없을 시 Interceptor로 로그인 페이지 이동

------

### 📚 What I Leaned?

- Spring Framework의 동작 원리를 이해하고 응용할 수 있습니다.
  - Component scan 방법과 Java 코드로 Spring Bean을 등록하는 방법 등 Spring Bean에 대한 이해
  - DI(의존성 주입)에 대한 이해와 생성자 주입, 필드 주입, 수정자 주입의 장단점 등
- HttpURLConnection 클래스를 사용해 외부 API를 호출할 수 있습니다.
- RDBMS 기본 SQL 문법을 작성할 수 있습니다.
- Mybatis를 설정하고 사용할 수 있습니다.
- Bcrypt를 암호화 해시 함수를 사용해 비밀번호 암호화를 할 수 있습니다.
- JSP, JavaScript, jQuery, Bootstrap 등을 사용하여 화면을 구성할 수 있습니다.

