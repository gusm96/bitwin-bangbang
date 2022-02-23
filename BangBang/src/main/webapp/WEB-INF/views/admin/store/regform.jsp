<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 등록</title>
    <style>
      .text_red {
        color: red;
      }

      .text_blue {
        color: blue;
      }
    </style>
    <%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/js/storeCheckid.js"/>"></script>
  </head>
  <body>
    <form method="post">
      <table>
        <tr>
          <td>아이디</td>
          <td>
            <input type="text" name="storeId" id="storeId" required />
            <div id="msg"></div>
          </td>
        </tr>
        <tr>
          <td>가맹점 이름</td>
          <td><input type="text" name="sname" id="sname" required /></td>
        </tr>
        <tr>
          <td>가맹점 전화번호</td>
          <td><input type="text" name="sphone" id="sphone" required /></td>
        </tr>
        <tr>
          <td>가맹점 주소</td>
          <td><input type="text" name="address" id="address" required /></td>
        </tr>
        <tr>
          <td>사업자명</td>
          <td><input type="text" name="oname" id="oname" required /></td>
        </tr>
        <tr>
          <td>사업자 전화번호</td>
          <td><input type="text" name="ophone" id="ophone" required /></td>
        </tr>
        <tr>
          <td>사업자 이메일</td>
          <td><input type="email" name="oemail" id="oemail" required /></td>
        </tr>
        <tr>
          <td>사업자 등록번호</td>
          <td><input type="text" name="regnum" id="regnum" required /></td>
        </tr>
        <tr>
          <td><button type="submit">등록하기</button></td>
        </tr>
      </table>
    </form>
  </body>
</html>
