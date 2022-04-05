<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result eq Y }">
		<script>
		 alert("요청을 거절하였습니다.");
         location.href = "${pageConext.request.contextPath}/admin/store";
		</script>
</c:if>
<c:if test="${result eq N }">
		<script>
		 alert("실패하였습니다.");
         location.reload();
		</script>
</c:if>