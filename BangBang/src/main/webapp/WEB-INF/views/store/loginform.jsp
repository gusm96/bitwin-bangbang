<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty storeInfo }">
	<script>
		location.href = "/bangbang/store";
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 로그인</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#main_container{
	width: 300px;
	padding-bottom: 50px;
}
form * input{
margin-bottom: 5px;
}
</style>
</head>
<body>
	<%@include file="../includes/store-header.jsp"%>
	<%@include file="../includes/store-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<h3>가맹점 로그인</h3>
			<form method="post">
				<div class="form-floating">
					<label>아이디</label> <input class="form-control" type="text"
						name="storeId" id="storeId" required placeholder="아이디를 입력하세요." />
				</div>
				<div class="form-floating">
					<label>비밀번호</label> <input class="form-control" type="password"
						name="storePw" id="storePw" required placeholder="비밀번호를 입력하세요." />
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
