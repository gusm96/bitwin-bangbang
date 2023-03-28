<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 상세정보</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/resources/css/container.css">
<style type="text/css">
	table {
	margin: 0;
	width: 100%;
}
</style>
</head>
<body>
	<%@include file="../includes/store-header.jsp"%>
	<%@include file="../includes/store-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<table class="table" style="text-align: left;">
				<thead>
					<tr>
						<th colspan="3" style="text-align: center;"><h3>${store.sname}</h3></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>가맹점 사진</th>
						<td><img
							src="${pageContext.request.contextPath}/resources/uploadfile/${store.photo}"
							style="width: 200px; height: 200px; border-radius: 15px;" /></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${store.storeId}</td>
					</tr>
					<tr>
						<th>가맹점명</th>
						<td>${store.sname}</td>
					</tr>
					<tr>
						<th>가맹점 전화번호</th>
						<td>${store.sphone}</td>
					</tr>
					<tr>
						<th>가맹점 주소</th>
						<td>${store.address}</td>
					</tr>
					<tr>
						<td></td>
						<td><button id="update_request_btn">가맹점 정보 수정</button></td>
					</tr>
				</tbody>
			</table>
			<table class="table" style="text-align: left;">
				<tr>
					<th>사업자명</th>
					<td>${store.oname}</td>
				</tr>
				<tr>
					<th>사업자 전화번호</th>
					<td>${store.ophone}</td>
				</tr>
				<tr>
					<th>사업자 이메일</th>
					<td>${store.oemail}</td>
				</tr>
				<tr>
					<th>사업자 번호</th>
					<td>${store.regnum}</td>
				</tr>
				<tr>
					<th>가맹점 등록일</th>
					<td>${store.date}</td>
				</tr>
			</table>
			<button id="changepw_btn">비밀번호 변경</button>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
	<script>
		$("#changepw_btn").click(function() {
			location.href = "mypage/edit/pw";
		});
		$("#update_request_btn").click(function() {
			location.href = "mypage/edit/";
		});
	</script>
	
</body>
</html>
