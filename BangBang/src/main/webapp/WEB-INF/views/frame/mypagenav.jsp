<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<div id="mypage_nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/mypage/edit">내정보 수정</a></li>
			<li><a href="">장바구니</a></li>
			<li><a href="">찜 목록</a></li>
			<li><a href="">쿠폰 / 적립금</a></li>
			<li><a href="">1:1 문의 내역</a></li>
		</ul>
	</div>
</body>
</html>