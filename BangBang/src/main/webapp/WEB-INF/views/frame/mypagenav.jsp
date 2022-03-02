<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#mypage_nav {
	width: 200px;
	text-align: center;
	border: 1px solid black;
	border-radius: 15px;
	padding: 10px 10px;
	box-shadow: 5px 5px 7px rgb(0, 0, 0, 0.5);
	margin-top: 50px;
	margin-right: 150px;
	text-align: center;
	height: 100%;
}

#mypage_ul {
	list-style: none;
	padding: 30px 30px;
	margin: 0;
	display:flex;
	justify-content: space-between;
	flex-direction: column;
	width: 100%;
	height: 100%
}

#mypage_ul>li {
	margin-bottom: 10px;
}

#mypage_ul * a {
	text-decoration: none;
	color: inherit;
	font-weight: bold;
}
</style>
<nav id="mypage_nav">
	<ul id="mypage_ul">
		<li><a
			href="${pageContext.request.contextPath}/member/mypage">내정보
				수정</a></li>
		<li><a href="">장바구니</a></li>
		<li><a href="">찜 목록</a></li>
		<li><a href="">쿠폰 / 적립금</a></li>
		<li><a
			href="${pageContext.request.contextPath}/member/paq/member-list">1:1
				문의 내역</a></li>
	</ul>
</nav>

