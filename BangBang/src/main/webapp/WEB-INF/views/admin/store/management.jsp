<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가맹점 관리</title>
</head>
<body>
	<div>
		<form>
			<div style="display: inline;">가맹점 검색</div>
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
						<th colspan="4">가맹점 리스트</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>가맹점 번호</td>
						<td>가맹점 이름</td>
						<td>가맹점 주소</td>
						<td>사업자명</td>
					</tr>
					<c:forEach items="${store}" var="s">
						<tr>
							<td>${s.sidx}</td>
							<td>${s.sname}</td>
							<td>${s.address}</td>
							<td>${s.oname}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>