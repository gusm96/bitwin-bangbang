<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>회원가입</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/resources/css/container.css">
<style>
#msg {
	display: none;
}

.text_red {
	color: red;
}

.text_blue {
	color: blue;
}

table {
	width: 80%;
	margin: 0;
}

#main_container {
	width: 450px;
}

.form-floating {
	margin-bottom: 5px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/checkid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/checkemail.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/checkpw.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/reg.js"></script>
</head> 
<body>
	<%@include file="../includes/bangbang-header.jsp"%>

	<%@include file="../includes/bangbang-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<h3>일반 회원가입</h3>
			<hr>
			<form method="POST" id="form">
				<div class="form-floating">
					<label>아이디</label> <input class="form-control" type="text"
						name="userid" id="userid" required min="5" max="30" />
					<div id="msg"></div>
				</div>
				<div class="form-floating">
					<label>비밀번호</label> <input class="form-control" type="password"
						name="password" id="password1" min="8" max="16" required />
					<div style="font-size: small; color: gray;"># 영문, 숫자, 기호를
						사용하여 8자 이상 16자 이하로 입력하세요.</div>
				</div>

				<div class="form-floating">
					<label>비밀번호 확인</label> <input class="form-control" type="password"
						name="password2" id="password2" min="8" max="16" required />
					<div id="pmsg"></div>
				</div>
				<div class="form-floating">
					<label>이름</label> <input class="form-control" type="text"
						name="username" id="username" required />
				</div>
				<div class="form-floating">
					<label>생년월일</label> <input class="form-control" type="date"
						name="birth" required />
				</div>
				<div class="form-floating">
					<label>전화번호</label> <input class="form-control" type="text"
						name="phonenum" required />
				</div>
				<div class="form-floating">
					<label>이메일</label> <input class="form-control" type="email"
						name="email" id="email" required />
					<div id="emsg"></div>
				</div>
				<hr />
				<span style="font-weight: bolder;">광고 및 알람 수신여부</span><br />
				<hr>
				<table>
					<tr>
						<td>이메일</td>
						<td><input type="checkbox" name="enotify" /></td>
					</tr>
					<tr>
						<td>문자</td>
						<td><input type="checkbox" name="mnotify" /></td>
					</tr>
					<tr>
						<td>카카오톡</td>
						<td><input type="checkbox" name="snotify" /></td>
					</tr>
					<tr>
						<td></td>
						<td><hr>
							<button name="submit" id="submit" type="submit"
								class="w-100 btn btn-lg btn-primary">가입하기</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
</body>
</html>
