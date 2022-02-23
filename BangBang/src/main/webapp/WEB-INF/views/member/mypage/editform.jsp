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
</head>
<body>
	<h3>${member.username}님의 회원정보</h3>
	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="userid" value="${member.userid}">
		<table>
			<c:if test="${loginType eq 'general'}">
				<tr>
					<td>프로필</td>
					<td><img
						src="${pageContext.request.contextPath}/resources/uploadfile/${member.photo}"
						width="200px" height="200px"> <input type="hidden"
						name=oldPhoto value="${member.photo}"></td>
					<td><input type="file" name="photo"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><div id="change_pw">
							<a
								href="${pageContext.request.contextPath}/member/mypage/edit/pw">변경하기</a>
						</div></td>
				</tr>
			</c:if>
			<tr>
				<td>생년월일</td>
				<td>${member.birth}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phonenum" required
					value="${member.phonenum }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<c:if test="${loginType eq 'general'}">
					<td><input type="email" name="email" id="email"
						value="${member.email}" required />
						<div id="emsg"></div></td>
				</c:if>
				<c:if test="${loginType eq 'kakao' || loginType eq 'naver'}">
					<td><input type="hidden" name="email" id="email"
						value="${member.email}" /> ${member.email}</td>
				</c:if>
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
						<input type="checkbox" name="mnotify" checked>
					</c:if> <c:if test="${member.mnotify eq false}">
						<input type="checkbox" name="mnotify">
					</c:if></td>
			</tr>
			<tr>
				<td>카카오톡</td>
				<td><c:if test="${member.snotify eq true}">
						<input type="checkbox" name="snotify" checked>
					</c:if> <c:if test="${member.snotify eq false}">
						<input type="checkbox" name="snotify">
					</c:if></td>
			</tr>
			<tr>
				<td><a href="../mypage">취소하기</a></td>
				<td><button type="submit" name="submit">수정하기</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
