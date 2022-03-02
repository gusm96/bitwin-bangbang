<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${loginInfo ne null}">
		<h1>일반회원 로그인 성공</h1>
	</c:if>
	<c:if test="${loginInfo eq null}">
		<h1>로그아웃 성공</h1>
	</c:if>

</body>
</html>