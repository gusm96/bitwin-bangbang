<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result > 0 }">
	<script>
		alert("이메일로 회원님의 아이디를 전송하였습니다.");
		location.href = "/bangbang/login/store";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script>
		alert("해당 이메일로 등록된 가맹점이 없습니다.");
		history.go(-1);
	</script>
</c:if>