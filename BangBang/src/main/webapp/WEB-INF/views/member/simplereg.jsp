<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>간편 회원 가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/container.css">
<style type="text/css">
#main_container {
	width: 300px;
}
</style>
</head>
<body>
	<%@include file="../includes/bangbang-header.jsp"%>

	<%@include file="../includes/bangbang-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<h3>간편 회원가입</h3>
			<hr>
			<form method="post">
				<input type="checkbox" name="sns" checked="checked"	style="display: none;"> 
				<input type="hidden" name="photo" value="${userInfo.profile}" /> 
				<input type="hidden" name="userid"	value="${userInfo.email}" />
				<input type="hidden" name="username" value="${userInfo.nickname}"required />
				<div class="form-floating">
					<label for="birth">생년월일 </label><input class="form-control"
						type="date" name="birth" required />
				</div>
				<div class="form-floating">
					<label for="phonenum">전화번호</label>
					<c:if test="${empty userInfo.phonenum }">
						<input class="form-control" type="text" name="phonenum" required />
					</c:if>
					<c:if test="${not empty userInfo.phonenum}">
						<input type="hidden" name="phonenum" value="${userInfo.phonenum}">
         		  ${userInfo.phonenum}
          		 </c:if>

				</div>
				<div>
					<input type="hidden" name="email" value="${userInfo.email}" />
				</div>
				<hr>
				<div style="font-weight: bolder;">광고 및 알림 수신동의</div>
				<hr>
				<div>
					<label for="enotify">이메일<input type="checkbox"
						name="enotify" /></label>
				</div>
				<div>
					<label for="mnotify">문자<input type="checkbox"
						name="mnotify" /></label>
				</div>
				<div>
					<label for="snotify">카카오톡<input type="checkbox"
						name="snotify" /></label>
				</div>
				<div>
					<button type="submit" class="w-100 btn btn-lg btn-primary">회원가입</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>
</body>
</html>
