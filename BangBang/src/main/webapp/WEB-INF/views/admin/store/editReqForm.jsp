<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>가맹점 정보 수정 요청</title>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#main_container {
	padding: 20px 20px;
}

table {
	width: 100%;
}

td {
	text-align: center;
}
</style>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container" class="text-center">
			<h3>가맹점 정보 수정 요청</h3>
			<hr>
			<form method="post">
				<input type="hidden" name="sidx" value="${store.sidx}" />
				<table>
					<tr>
						<th>가맹점명</th>
						<td><span>${store.sname}</span> <input type="hidden"
							name="sname" value="${store.sname}" required /></td>
					</tr>
					<tr>
						<th>가맹점 번호</th>
						<td><span>${store.sphone}</span> <input type="hidden"
							name="sphone" value="${store.sphone}" required /></td>
					</tr>
					<tr>
						<th>가맹점 주소</th>
						<td><span>${store.address}</span> <input type="hidden"
							name="address" value="${store.address}" required /></td>
					</tr>
				</table>
				<button id="cancel_btn" type="button">거절</button>
				<button id="edit_btn" type="submit" style="display: inline;">수정</button>
			</form>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
	<script>
		$("#cancel_btn").click(function() {
			if (confirm("정말로 거절하시겠습니까?")) {
				alert("요청을 거절하였습니다.");
				location.href = "/bangbang/admin/store/${store.sidx}/cancel";
			}
		});
	</script>
</body>
</html>
