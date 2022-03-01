<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 정보</title>
  </head>
  <body>
    <%@include file="../includes/store-header.jsp"%> <%@include
    file="../includes/store-nav.jsp"%>
    <div id="body_container">
      <div>
        <h3>${storeInfo.sname}</h3>
        <img
          src="${pageContext.request.contextPath}/resources/uploadfile/${storeInfo.photo}"
          style="width: 50px; height: 50px"
        />
      </div>
    </div>
  </body>
</html>
