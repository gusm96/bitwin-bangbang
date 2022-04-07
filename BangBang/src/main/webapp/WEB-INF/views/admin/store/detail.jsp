<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 상세정보</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<table class="table">
				<thead>
					<tr >
						<th colspan="2" style="text-align: center;"><h3>${store.sname}</h3></th>
					</tr>
				</thead>
				<tbody style="text-align: left;">
					<tr>
						<th>가맹점 사진</th>
						<td><img
							src="${pageContext.request.contextPath}/resources/uploadfile/${store.photo}"
							style="width: 200px; height: 200px;"></td>
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
				</tbody>
			</table>
			<button id="edit_btn" value="${store.sname}">정보수정</button>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
	<script>
		$("#edit_btn").click(function() {
			location.href = $("#edit_btn").val() + "/edit";
		});
	</script>
</body>
</html>
