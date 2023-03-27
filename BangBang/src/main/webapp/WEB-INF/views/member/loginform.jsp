<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>로그인</title>
<%@include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/resources/css/container.css">
<style type="text/css">
#main_container {
	width: 343px;
}

form * {
	margin-bottom: 5px;
}
</style>

</head>
<body>
	<%@include file="../includes/bangbang-header.jsp"%>
	<%@include file="../includes/bangbang-nav.jsp"%>
	<div id="body_container">
		<div id="main_container" class="text-center">
			<h3>로그인</h3>
			<form method="post">
				<input type="hidden" name="url" value="${param.referer}" />
				<div class="form-floating">
					<label for="userid">아이디</label> <input type="text"
						class="form-control" name="userid" required
						onkeyup="charCheck(this)" placeholder="아이디를 입력하세요."
						value="${cookie.saveId != null ? cookie.saveId.value : ''}" />
				</div>
				<div class="form-floating">
					<label for="password">비밀번호</label> <input type="password"
						name="password" class="form-control" required
						onkeyup="charCheck(this)" placeholder="비밀번호를 입력하세요." />
				</div>
				<div>
					<label>아이디 저장</label> <input type="checkbox" name="saveid"
						${cookie.saveId != null ? 'checked' : ' '}>
				</div>
				<div>
					<button type="submit" class="w-100 btn btn-lg btn-primary">
						로그인</button>
				</div>
			</form>
			<div style="margin-bottom: 5px;">
				<a
					href="https://kauth.kakao.com/oauth/authorize?client_id=${kakao.client_id}&redirect_uri=${kakao.redirect_uri}&response_type=code"><img
					src="${pageContext.request.contextPath}/resources/images/kakao_login_medium_wide.png" /></a>
			</div>
			<div>
				<a
					href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${naver.client_id}&redirect_uri=${naver.redirect_uri}&state=hLiDdL2uhPtsftcU
 "><img
					src="${pageContext.request.contextPath}/resources/images/naver_login.png"
					width="300px" height="45px" /></a>
			</div>
			<br>
			<div>
				<a href="${pageContext.request.contextPath }/login/store">가맹점
					로그인</a>
			</div>
			<hr />
			<div>
				<a href="${pageContext.request.contextPath}/member/search/id">아이디
					찾기</a> <a href="${pageContext.request.contextPath}/member/search/pw">비밀번호
					찾기</a>
			</div>
			<span>아직 회원이 아니신가요?</span> <br /> &rarr; <a
				href="${pageContext.request.contextPath }/member/join/general">회원가입</a>
		</div>
	</div>
	<%@include file="../includes/bangbang-footer.jsp"%>

	<script type="text/javascript">
		/* 특수문자 및 공백 사용 불가. */
		function charCheck(obj) {
			var regExp = /[\{\}\[\]\/?.,;:|\)~`\-_+┼<>\\'\"\\\(\=]/gi;
			var space = /\s/g;
			if (regExp.test(obj.value) || obj.value.match(space)) {
				alert("공백 및 특수문자(!@#$%^&*제외)는 사용할 수 없습니다.");
				obj.value = obj.value.substring(0, obj.value.length - 1);
			}
		}
	</script>
</body>
</html>
