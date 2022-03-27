<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가맹점 관리</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
<link rel="stylesheet" href="/bangbang/resources/css/container.css">
<style type="text/css">
#edit_req_box {
	height: 30px;
	padding: 5px 5px;
	border: 1px soid black;
	border-radius: 10px;
	background: gray;
	color: white;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="../../includes/admin-header.jsp"%>
	<%@include file="../../includes/admin-nav.jsp"%>
	<div id="body_container">
		<div id="main_container">
			<form>
				<h3>가맹점 검색</h3>
				<select name="keyword">
					<option value="sname"
						${param.searchType eq 'sname' ? 'selected' : ''}>가맹점명</option>
					<option value="oname"
						${param.searchType eq 'oname' ? 'selected' : ''}>사업자명</option>
				</select> <input type="text">
				<button type="submit">검색</button>
			</form>
			<hr>
			<div>
				<div>
					<h3>가맹점 리스트</h3>
					<span>&rarr;<a
						href="${pageContext.request.contextPath}/admin/store/reg">가맹점
							등록하기</a></span>
				</div>
				<table class="table" style="text-align: left; width: 100%;">
					<thead>
						<tr>
							<th>번호</th>
							<th>가맹점명</th>
							<th>가맹점 주소</th>
							<th>사업자명</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${store.list}" var="s">
							<tr>
								<td>${s.sidx}</td>
								<td>${s.sname}</td>
								<td>${s.address}</td>
								<td>${s.oname}</td>
								<td><a href="store/${s.sname}"><button>상세보기</button></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이징 넘버 영역 -->
				<div class="row justify-content-md-center mt-4 bottom">
					<div class="btn-toolbar" role="toolbar"
						style="display: flex; align-items: center; justify-content: center">
						<div class="btn-group mr-2" role="group">
							<c:if test="${store.pagination.preNum>0}">
								<a href="store?p=${store.pagination.preNum}"
									class="btn btn-primary">이전</a>
							</c:if>
							<c:forEach begin="${store.pagination.startNum}"
								end="${store.pagination.endNum}" var="pnum">
								<a href="store?p=${pnum}"
									class="btn ${store.pageNum eq pnum ? 'btn-dark': 'btn-white'}">${pnum}</a>
							</c:forEach>
							<c:if test="${store.pagination.nextNum>0}">
								<a href="store?p=${store.pagination.nextNum}"
									class="btn btn-primary">다음</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div id="edit_req_box">
				<span>가맹점 정보수정 요청</span> [<span style="color: red;"><c:out
						value="${storeReq}"></c:out></span>]
			</div>
		</div>
	</div>
	<%@include file="../../includes/bangbang-footer.jsp"%>
	<script type="text/javascript">
		$("#edit_req_box")
				.click(
						function() {
							location.href = "${pageContext.request.contextPath}/admin/store/modification-list";
						});
	</script>
</body>
</html>