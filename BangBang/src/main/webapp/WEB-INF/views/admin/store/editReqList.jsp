<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 정보수정 요청</title>
    <link rel="stylesheet" href="/resources/css/container.css" />
    <style type="text/css">
      #main_container {
        width: 700px;
      }
      table{
      	text-align: left;
      }
    </style>
  </head>
  <body>
    <%@include file="../../includes/admin-header.jsp"%> <%@include
    file="../../includes/admin-nav.jsp"%>
    <div id="body_container">
      <div id="main_container">
      	<h3>가맹점 정보수정 요청 리스트</h3>
        <table class="table">
          <thead>
            <tr>
              <th>신청 번호</th>
              <th>가맹점명</th>
              <th>가맹점 아이디</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          	<c:if test="${empty storeReq.list}">
          		<tr>
          		<td colspan="4" style="text-align: center;">요청이 없습니다.</td>
          		</tr>
          	</c:if>
            <c:forEach items="${storeReq.list}" var="sr">
              <tr>
                <td>${sr.sridx}</td>
                <td>${sr.sname}</td>
                <td>${sr.storeId}</td>
                <td>
                  <a
                    href="${pageContext.request.contextPath}/admin/store/${sr.sridx}/req"
                    >수정</a
                  >
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <%@include file="../../includes/bangbang-footer.jsp"%>
  </body>
</html>
