<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자 로그인</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#main_container {
	width: 300px;
}
form * input{
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<%@include file="../includes/admin-header.jsp"%>
	<%@include file="../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<h3>관리자 로그인</h3>
			<form method="post">
				<div class="form-floating">
					<label>아이디</label> <input class="form-control" type="text"
						name="aid" required />
				</div>
				<div class="form-floating">
					<label>비밀번호</label> <input class="form-control" type="password"
						name="apw" required />
				</div>
				<div>
					<button type="submit" class="w-100 btn btn-lg btn-primary">로그인</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
</body>
</html>
