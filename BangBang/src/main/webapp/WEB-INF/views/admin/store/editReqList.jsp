<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가맹점 정보수정 요청</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#main_container {
	width: 700px;
}
</style>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<table style="text-align: center; width: 100%;">
				<thead>
					<tr>
						<td colspan="4" style="font-weight: bolder;">가맹점 정보수정 요청<br></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>신청 번호</td>
						<td>가맹점명</td>
						<td>가맹점 아이디</td>
						<td></td>
					</tr>
					<c:forEach items="${storeReq.list}" var="sr">
						<tr>
							<td>${sr.sridx}</td>
							<td>${sr.sname}</td>
							<td>${sr.storeId}</td>
							<td><a
								href="${pageContext.request.contextPath}/admin/store/${sr.sridx}/req">수정</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>