<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>로그인</title>
</head>
<body>
	<form method="post">
		<input type="hidden" name="url" value="${param.referer}" />
		<div>
			<label for="userid">아이디</label> <input type="text" name="userid"
				required placeholder="아이디를 입력하세요."
				value="${cookie.saveId != null ? cookie.saveId.value : ''}" />
		</div>
		<div>
			<label for="password">비밀번호</label> <input type="password"
				name="password" required placeholder="비밀번호를 입력하세요." />
		</div>
		<div>
			<label>아이디 저장</label> <input type="checkbox" name="saveid"
				${cookie.saveId != null ? 'checked'
        : ' '}>
		</div>
		<div>
			<button type="submit">로그인</button>
		</div>
	</form>
	<hr />
	<div>
		<a
			href="https://kauth.kakao.com/oauth/authorize?client_id=${kakao.client_id}&redirect_uri=${kakao.redirect_uri}&response_type=code"><img
			src="${pageContext.request.contextPath}/resources/images/kakao_login_medium_wide.png" /></a>
	</div>
	<div>
		<a
			href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${naver.client_id}&redirect_uri=${naver.redirect_uri}&state=hLiDdL2uhPtsftcU
 ">네이버
			ICON</a>
	</div>
	<div>
		<a href="">구글 ICON</a>
	</div>
	<hr />
	<span>아직 회원이 아니신가요?</span>
	<br /> &rarr;
	<a href="${pageContext.request.contextPath }/member/join">회원가입</a>
</body>
</html>
