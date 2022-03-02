<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 등록</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style>
.text_red {
	color: red;
}

.text_blue {
	color: blue;
}
.form-floating{
	margin-bottom: 10px;
}
</style>
<script type="text/javascript"
	src="<c:url value="/resources/js/storeCheckid.js"/>"></script>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<h3>가맹점 등록</h3>
			<hr>	
			<form method="post">
				<div class="form-floating">
					<label for="storeId">아이디</label> <input class="form-control" type="text" name="storeId" id="storeId"
						required />
					<div id="msg"></div>
				</div>
				<div class="form-floating">
					<label for="sname">가맹점 이름</label> <input class="form-control" type="text" name="sname" id="sname"
						required />
				</div>
				<div class="form-floating">
					<label for="sphone">가맹점 전화번호</label> <input class="form-control" type="text" name="sphone"
						id="sphone" required />
				</div>
				<div class="form-floating">
					<label for="address">가맹점 주소</label> <input class="form-control" type="text" name="address"
						id="address" required />
				</div>
				<div class="form-floating">
					<label for="oname">사업자명</label> <input class="form-control" type="text" name="oname" id="oname"
						required />
				</div>
				<div class="form-floating">
					<label for="ophone">사업자 전화번호</label> <input class="form-control" type="text" name="ophone"
						id="ophone" required />
				</div>
				<div class="form-floating">
					<label for="oemail">사업자 이메일</label> <input class="form-control" type="email" name="oemail"
						id="oemail" required />
				</div>
				<div class="form-floating">
					<label for="regnum">사업자 등록번호</label> <input class="form-control" type="text" name="regnum"
						id="regnum" required />
				</div>
				<div>
					<button type="submit" class="w-100 btn btn-lg btn-primary">등록하기</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>
