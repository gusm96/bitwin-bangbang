<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>아이디 찾기</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/container.css">
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

#main_container {
	width: 300px;
}
#button_container{
	margin-top: 10px;
}
</style>
<script>
	$(document).ready(function() {
		$("#email").focusin(function() {
			$("#msg").css("display", "none");
			$("#msg").removeClass("text_blue");
			$("#msg").removeClass("text_red");
			$("#msg").text("");
		});

		$("#email").focusout(function() {
			if ($("email").val() == "") {
				$("#msg").css("display", "block");
				$("#msg").text("이메일을 입력하세요.");
				$("#msg").addClass("text_red");
			}
		});
	});
</script>
</head>
<body>
	<%@include file="../includes/bangbang-header.jsp"%>
	<%@include file="../includes/bangbang-nav.jsp"%>
	<div id="body_container">
		<div id="main_container" class="text-center">
			<div>
				<h3>아이디 찾기</h3>
				<span>등록된 이메일을 입력하세요.</span>
			</div>
			<form method="post">
				<div class="form-floating">
					<label for="email">이메일</label> <input class="form-control"
						type="email" name="email" id="email" required />
					<div id="msg"></div>
				</div>
				<div id="button_container">
					<button type="submit" style="display: inline">아이디 찾기</button>
					<button id="back_btn">뒤로가기</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		$("#back_btn").click(function() {
			history.go(-1);
		});
	</script>
	<%@include file="../includes/bangbang-footer.jsp"%>
</body>
</html>
