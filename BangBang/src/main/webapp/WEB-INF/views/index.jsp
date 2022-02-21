<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#profile {
	width: 50px;
	height: 50px;
	border-radius: 50%;
}
</style>
</head>
<body>
	<c:if test="${empty loginInfo }">
		<div>
			<a href="${pageContext.request.contextPath}/login">로그인</a> <a
				href="${pageContext.request.contextPath}/join">회원가입</a>
		</div>
	</c:if>
	<c:if test="${not empty loginInfo }">
		<div>
			<span>${loginInfo.username}</span>
			<c:if test="${not empty loginInfo.photo }">
				<img id="profile" src="${loginInfo.photo}">
			</c:if>
			<c:if test="${loginType eq 'sns'}">
			<a href="https://kauth.kakao.com/oauth/logout?client_id=${kakao.client_id}&logout_redirect_uri=${kakao.logout_redirect_uri}">로그아웃</a> 
			<a href="${pageContext.request.contextPath}/mypage/">마이페이지</a>
			</c:if>
			<c:if test="${loginType eq 'general'}">
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a> 
			<a href="${pageContext.request.contextPath}/mypage/">마이페이지</a>
			</c:if>
		</div>
	</c:if>
</body>
</html>