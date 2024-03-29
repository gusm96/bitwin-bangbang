<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/container.css">
<style type="text/css">
	form * input{
	margin-bottom: 5px;
	}
</style>
</head>
<body>
	<%@include file="../includes/store-header.jsp"%>
	<%@include file="../includes/store-nav.jsp"%>
	<div id="body_container">
		<div id="main_container" class="text-center">
			<form method="post">
				<div class="form-floating">
					<label for="sname">가맹점명</label> <input id="sname" class="form-control"
						type="text" name="sname" value="${store.sname}" required />
				</div>
				<div class="form-floating">
					<label for="sphone">가맹점 전화번호</label> <input id="sphone" class="form-control"
						type="text" name="sphone" value="${store.sphone}" required />
				</div>
				<div class="form-floating" >
					<label for="address">가맹점 주소</label> <input id="address" class="form-control"
						type="text" name="address" value="${store.address}" required />
				</div>
				<div>
					<button type="submit" id="submit_btn" class="w-100 btn btn-lg btn-primary">정보 수정 신청</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
</body>
</html>
