<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
	<h1>관리자 페이지 입니다.</h1>
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath}/admin/store">가맹점
					관리</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/member">회원 관리</a></li>
			<li><a href="">공지 게시글 관리</a></li>
			<li><a href="">상품 게시글 관리</a></li>
			<li><a href="">판매 상품 관리</a></li>
			<li><a href="">1:1 문의 목록 조회</a></li>
			<li><a href="${pageContext.request.contextPath }/admin/logout">로그아웃</a>
			</li>
		</ul>
	</nav>
</body>
</html>
