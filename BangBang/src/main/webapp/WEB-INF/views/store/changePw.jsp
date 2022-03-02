<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>비밀번호 변경</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<style>
#msg, #pmsg {
	display: none;
}

.text_red {
	color: red;
}

.text_blue {
	color: blue;
}
#changepw_container {
	text-align: center;
	margin-bottom: 50px;
}

#pwform {
	display: inline-block;
}
.form_input{
	display: flex;
	justify-content: space-between;
}
#changepw_container * input {
margin-bottom: 10px;
	width: 350px;
}
</style>
<script type="text/javascript"
	src="<c:url value="/resources/js/currentpw.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/checkpw.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/reg.js"/>"></script>
</head>
<body>
	<%@include file="../includes/store-header.jsp"%>

	<%@include file="../includes/store-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<div id="changepw_container">
				<form method="post" id="pwform">
					<h3>비밀번호 변경</h3>
					<hr>
					<div class="form_input">
						<label for="current_pw">현재 비밀번호</label><input type="password"
							name="currentPw" id="current_pw" placeholder="현재 비밀번호를 입력하세요."
							required />
					</div>
					<div id="msg"></div>
					<div class="form_input">
						<label for="new_password1">새 비밀번호</label> <input type="password"
							name="newPw1" id="password1"
							placeholder="영문, 숫자, 기호를 사용하여 8자 이상 입력하세요." min="8" max="16"
							required />
					</div>
					<div class="form_input">
						<label for="new_password2">새 비밀번호 확인</label> <input
							type="password" name="newPw2" id="password2"
							placeholder="새로운 비밀번호를 확인하세요." min="8" max="16" required />
					</div>
					<div id="pmsg"></div>
					<button type="button" id="back_btn">뒤로가기</button>
					<button type="submit" id="submit_btn">수정하기</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
	<script type="text/javascript">
		$("#back_btn").click(function() {
			history.go(-1);
		});
	</script>

</body>
</html>
