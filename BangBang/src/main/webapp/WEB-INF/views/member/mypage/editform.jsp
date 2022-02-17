<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>내 정보 수정</title>
<style>
.change_pw {
	display: none;
}
</style>

<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/checkpw.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/changepw.js"/>"></script>

</head>
<body>
	<h3>${member.username}님의회원정보</h3>
	<form method="post">
		<table>
			<tr>
				<td>프로필</td>
				<td><input type="file" name="photo"></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${member.userid}<input type="hidden" name="userid"
					value="${member.userid}">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td id="btn"><input type="button" id="change_btn" value="변경하기" />
				</td>
				<td>
					<div class="change_pw">
						<input type="password" name="passowrd" id="password"
							value="${member.password}" required />
					</div>
				</td>
			</tr>
			<tr class="change_pw">
				<td>비밀번호 확인</td>
				<td><input type="password" name="passowrd2" id="password2"
					value="${member.password}" required />
					<div id="pw2"></div></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.birth}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>전화번호 변경</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" id="email"
					value="${member.email}" required />
					<div id="emsg"></div></td>
			</tr>
		</table>
		<hr>
		광고 및 알람 수신동의
		<table>
			<tr>
				<td>이메일</td>
				<td><c:if test="${member.enotify eq true}">
						<input type="checkbox" name="enotify" checked>
					</c:if> <c:if test="${member.enotify eq false}">
						<input type="checkbox" name="enotify">
					</c:if></td>
			</tr>
			<tr>
				<td>문자</td>
				<td><c:if test="${member.mnotify eq true}">
						<input type="checkbox" name="enotify" checked>
					</c:if> <c:if test="${member.mnotify eq false}">
						<input type="checkbox" name="enotify">
					</c:if></td>
			</tr>
			<tr>
				<td>카카오톡</td>
				<td><c:if test="${member.snotify eq true}">
						<input type="checkbox" name="enotify" checked>
					</c:if> <c:if test="${member.snotify eq false}">
						<input type="checkbox" name="enotify">
					</c:if></td>
			</tr>
		</table>
		<hr>
		<table>
			<tr>
				<td></td>
				<td><input type="submit" value="수정" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
