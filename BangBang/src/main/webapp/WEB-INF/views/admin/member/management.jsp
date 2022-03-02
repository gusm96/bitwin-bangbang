<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#main_container{
width: 700px;
}
</style>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<form>
				<h3>회원검색</h3>
				<select name="searchType">
					<option value="userid"
						${param.searchType eq 'userid' ? 'selected' : ''}>아이디</option>
					<option value="username"
						${param.searchType eq 'username' ? 'selected' : ''}>이름</option>
				</select> <input type="text">
				<button type="submit">검색</button>
			</form>
			<hr>
			<div>
				<h3>회원 리스트</h3>
				<table style="text-align: center; width: 100%;">
					<tbody>
						<tr>
							<td>회원번호</td>
							<td>아이디</td>
							<td>이름(닉네임)</td>
							<td>이메일</td>
						</tr>
						<c:forEach items="${member}" var="m">
							<tr>
								<td>${m.uidx}</td>
								<td>${m.userid}</td>
								<td>${m.username}</td>
								<td>${m.email}</td>
								<td><a href="member/${m.uidx}"><button>상세보기</button></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>