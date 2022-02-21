<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
</head>
<body>
	<div>
		<form>
			<div style="display: inline;">회원검색</div>
			<select name="searchType" class="form-control">
				<option value="userid"
					${param.searchType eq 'userid' ? 'selected' : ''}>아이디</option>
				<option value="username"
					${param.searchType eq 'username' ? 'selected' : ''}>이름</option>
			</select> <input type="text">
			<button type="submit">검색</button>
		</form>
		<div>
			<table style="text-align: center">
				<thead>
					<tr>
						<th colspan="4">회원 리스트</th>
					</tr>
				</thead>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>