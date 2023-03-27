<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<link rel="stylesheet" href="/resources/css/container.css">
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
			<form method="get">
				<h3>회원검색</h3>
				<select name="keyword">
					<option value="userid"
						${param.searchType eq 'userid' ? 'selected' : ''}>아이디</option>
					<option value="username"
						${param.searchType eq 'username' ? 'selected' : ''}>이름</option>
				</select> <input type="text" name="search" placeholder="검색할 내용을 입력하세요.">
				<button type="submit">검색</button>
			</form>
			<hr>
			<div>
				<h3>회원 리스트</h3>
				<table class="table" style="text-align: left; width: 100%;">
					<thead>
						<tr>
							<th>회원번호</th>
							<th>아이디</th>
							<th>이름(닉네임)</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${member.list}" var="m">
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
				<!-- 페이징 넘버 영역 -->
				<div class="row justify-content-md-center mt-4 bottom">
					<div class="btn-toolbar" role="toolbar"
						style="display: flex; align-items: center; justify-content: center">
						<div class="btn-group mr-2" role="group">
							<c:if test="${member.pagination.preNum>0}">
								<a href="member?p=${member.pagination.preNum}"
									class="btn btn-primary">이전</a>
							</c:if>
							<c:forEach begin="${member.pagination.startNum}"
								end="${member.pagination.endNum}" var="pnum">
								<a href="member?p=${pnum}"
									class="btn ${member.pageNum eq pnum ? 'btn-dark': 'btn-white'}">${pnum}</a>
							</c:forEach>
							<c:if test="${member.pagination.nextNum>0}">
								<a href="member?p=${member.pagination.nextNum}"
									class="btn btn-primary">다음</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>