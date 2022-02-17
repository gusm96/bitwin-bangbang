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
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" required /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" required /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="로그인" /></td>
			</tr>
		</table>
	</form>
	<hr />
	<div>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=${kakao.client_id}&redirect_uri=${kakao.redirect_uri}&response_type=code"><img
			src="resources/images/kakao_login_medium_wide.png" /></a>
	</div>
	<div>
		<a href="">네이버 ICON</a>
	</div>
	<div>
		<a href="">구글 ICON</a>
	</div>
	<hr />
	<span>아직 회원이 아니신가요?</span>
	<br /> &rarr;
	<a href="${pageContext.request.contextPath }/join">회원가입</a>
</body>
</html>
