<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>회원 상세정보</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
table {
	width: 100%;
}

img {
	border-radius: 15px;
}
</style>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<table class="table">
				<thead>
					<tr>
						<th colspan="2" style="text-align: center;"><h3>${member.username}</h3></th>
					</tr>
				</thead>
				<tbody style="text-align: left;">
					<tr>
						<th>프로필</th>
						<td><c:if test="${member.sns == true}">
								<img src="${member.photo}" width="200px" height="200px">

							</c:if> <c:if test="${member.sns == false}">
								<img
									src="${pageContext.request.contextPath}/resources/uploadfile/${member.photo}"
									width="200px" height="200px" />

							</c:if></td>
					</tr>
					<c:if test="${member.sns == false }">
						<tr>
							<th>아이디</th>
							<td>${member.userid}</td>
						</tr>
					</c:if>
					<tr>
						<th>생년월일</th>
						<td>${member.birth}</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${member.phonenum}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${member.email}</td>
					</tr>
				</tbody>
			</table>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th colspan="2" style="text-align: center;"><h4>광고 및 알람
								수신동의</h4></th>
					</tr>
				</thead>
				<tbody style="text-align: left;">
					<tr>
						<th>이메일</th>
						<td>${member.enotify == true ? '허용' : '비허용'}</td>
					</tr>
					<tr>
						<th>문자</th>
						<td>${member.mnotify == true ? '허용' : '비허용'}</td>
					</tr>
					<tr>
						<th>카카오톡</th>
						<td>${member.snotify == true ? '허용' : '비허용'}</td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<thead>
					<tr>
						<th colspan="2" style="text-align: center;"><h4>회원가입일</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">${member.regdate}</td>
					</tr>
				</tbody>

			</table>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>
