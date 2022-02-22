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
	<h1>회원가입</h1>
	<div id="reg-container">
		<div id="general-reg" class="reg">
			<a href="${pageContext.request.contextPath}/member/join/general">일반 회원가입</a>
		</div>
		<div id="store-reg" class="reg">
			<a href="">가맹점 등록</a>
		</div>
	</div>
</body>
</html>