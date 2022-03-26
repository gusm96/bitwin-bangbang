<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>내 정보 수정</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style>
.change_pw {
	display: none;
}

#profile_img {
	border-radius: 15px;
	width: 150px;
	height: 150px;
}

table {
	width: 100%;
	border-collapse: collapse;
}
.line{
	border-bottom: 1px soild gray;
}
.line td {
 height:45px;
}
.line * input{
	outline:none;
	width: 100%;
	height: 80%;
	border-radius: 10px;
}
#edit_btn{
	margin-top: 20px;
}

</style>

<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<%@include file="../../includes/bangbang-header.jsp"%>
<%@include file="../../includes/bangbang-nav.jsp"%>
</head>
<body>
	<div id="body_container">
		<%@ include file="/WEB-INF/views/frame/mypagenav.jsp"%>
		<div id="main_container">
			<h3>${member.username}님의 회원정보</h3>
			<hr>
			<form method="post" enctype="multipart/form-data">
				<input type="hidden" name="userid" value="${member.userid}">
				<table style="text-align: center;">
					<c:if test="${loginType eq 'general'}">
						<tr class="line">
							<td><img
								src="${pageContext.request.contextPath}/resources/uploadfile/${member.photo}"
								id="profile_img"> <input type="hidden" name=oldPhoto
								value="${member.photo}">
								<hr></td>
							<td><input type="file" name="photo" ></td>
						</tr>
						<tr class="line">
							<th>비밀번호</th>
							<td><div id="change_pw">
									<a href="${pageContext.request.contextPath}/member/mypage/pw">변경하기</a>
								</div></td>
						</tr>
					</c:if>
					<tr class="line">
						<th>생년월일</th>
						<td>${member.birth}</td>
					</tr>
					<tr class="line">
						<th>전화번호</th>
						<td><input type="text" name="phonenum" required
							value="${member.phonenum }"></td>
					</tr>
					<tr class="line">
						<th>이메일</th>
						<c:if test="${loginType eq 'general'}">
							<td><input type="email" name="email" id="email"
								value="${member.email}" required />
								<div id="emsg"></div></td>
						</c:if>
						<c:if test="${loginType eq 'kakao' || loginType eq 'naver'}">
							<td><input type="hidden" name="email" id="email"
								value="${member.email}" /> ${member.email}</td>
						</c:if>
					</tr>
				</table>
				<hr>
				<div style="font-weight: bolder;">광고 및 알람 수신동의</div><br>
				<label>이메일</label>
				<c:if test="${member.enotify eq true}">
					<input  type="checkbox" name="enotify" checked>
				</c:if>
				<c:if test="${member.enotify eq false}">
					<input  type="checkbox" name="enotify">
				</c:if>

				<label>문자</label>
				<c:if test="${member.mnotify eq true}">
					<input type="checkbox" name="mnotify" checked>
				</c:if>
				<c:if test="${member.mnotify eq false}">
					<input type="checkbox" name="mnotify">
				</c:if>

				<label>카카오톡</label>
				<c:if test="${member.snotify eq true}">
					<input type="checkbox" name="snotify" checked>
				</c:if>
				<c:if test="${member.snotify eq false}">
					<input type="checkbox" name="snotify">
				</c:if>
				<button type="submit" name="submit" id="edit_btn"
					class="w-100 btn btn-lg btn-primary">수정하기</button>
			</form>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
</body>
</html>
