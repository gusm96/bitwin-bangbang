<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>일반 회원가입</h3>
	<form method="post">
		<table>
			<!-- 아이디 비밀번호 이름 생년월일 전화번호 이메일 이메일(수신여부) 문자(수신여부) 카카오톡(수신여부) -->
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" required></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" required></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="password2" required></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="username" required></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type="date" name="birth" required></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phonenum" required></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email" required></td>
			</tr>
		</table>
		<hr>
		<span>광고및 알람 수신여부</span><br>
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="checkbox" name="enotify" required></td>
			</tr>
			<tr>
				<td>문자</td>
				<td><input type="checkbox" name="mnotify" required></td>
			</tr>
			<tr>
				<td>카카오톡</td>
				<td><input type="checkbox" name="snotify" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
</body>
</html>