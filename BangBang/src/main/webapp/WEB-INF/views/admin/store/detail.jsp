<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 상세정보</title>
    <%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
  </head>
  <body>
    <div>
      <table>
        <thead>
          <tr>
            <th colspan="2">${store.sname}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>가맹점 사진</td>
            <td>${store.photo}</td>
          </tr>
          <tr>
            <td>아이디</td>
            <td>${store.storeId}</td>
          </tr>
          <tr>
            <td>가맹점명</td>
            <td>${store.sname}</td>
          </tr>
          <tr>
            <td>가맹점 전화번호</td>
            <td>${store.sphone}</td>
          </tr>
          <tr>
            <td>가맹점 주소</td>
            <td>${store.address}</td>
          </tr>
          <tr>
            <td>사업자명</td>
            <td>${store.oname}</td>
          </tr>
          <tr>
            <td>사업자 전화번호</td>
            <td>${store.ophone}</td>
          </tr>
          <tr>
            <td>사업자 이메일</td>
            <td>${store.oemail}</td>
          </tr>
          <tr>
            <td>사업자 번호</td>
            <td>${store.regnum}</td>
          </tr>
          <tr>
            <td>가맹점 등록일</td>
            <td>${store.date}</td>
          </tr>
        </tbody>
      </table>
      <hr />
      <button id="edit_btn" value="${store.sname}">정보수정</button>
    </div>
    <script>
      $("#edit_btn").click(function () {
        location.href = $("#edit_btn").val() + "/edit";
      });
    </script>
  </body>
</html>
