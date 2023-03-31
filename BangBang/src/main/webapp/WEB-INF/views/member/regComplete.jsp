<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result == 1 }">
	<script>
		alert("회원가입을 축하드립니다.");
		location.href = "${pageContext.request.contextPath}/member/login";
	</script>
</c:if>
<c:if test="${result == 2 }">
	<script>
		alert("아이디가 중복되었습니다.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result == 3 }">
	<script>
		alert("이메일이 중복되었습니다.");
		history.go(-1);
	</script>
</c:if>
