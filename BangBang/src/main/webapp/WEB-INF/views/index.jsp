<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>	
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<c:if test="${empty loginInfo }">
		<div>
			<a href="${pageContext.request.contextPath}/login">로그인</a>
			<a href="${pageContext.request.contextPath}/join">회원가입</a>
		</div>
	</c:if>
	<c:if test="${not empty loginInfo }">
		<div>
		<span>${loginInfo.userName}</span>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			<a href="${pageContext.request.contextPath}/mypage/">마이페이지</a>
		</div>
	</c:if>
</body>
</html>