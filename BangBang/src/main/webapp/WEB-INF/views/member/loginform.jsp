<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form mathod="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" required></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" required></td>
			</tr>
		</table>
	</form>
	<hr>
	<span>아직 회원이 아니신가요?</span>
	<br> &rarr;
	<a href="${pageContext.request.contextPath }/join">회원가입</a>
</body>
</html>