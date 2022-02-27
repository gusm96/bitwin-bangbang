<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원 상세정보</title>
    <%@include file="../../includes/admin-header.jsp"%> <%@include
    file="../../includes/admin-nav.jsp"%>
  </head>
  <body>
    <table>
      <thead>
        <tr>
          <th colspan="2">${member.username}</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>프로필</td>
          <td>
            <img
              src="${pageContext.request.contextPath}/resources/uploadfile/${member.photo}"
              width="200px"
              height="200px"
            />
          </td>
        </tr>
        <tr>
          <td>아이디</td>
          <td>${member.userid}</td>
        </tr>
        <tr>
          <td>생년월일</td>
          <td>${member.birth}</td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td>${member.phonenum}</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>${member.email}</td>
        </tr>
        <tr>
          <td colspan="2">광고 및 알람 수신동의</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>${member.enotify}</td>
        </tr>
        <tr>
          <td>문자</td>
          <td>${member.mnotify}</td>
        </tr>
        <tr>
          <td>카카오톡</td>
          <td>${member.snotify}</td>
        </tr>
        <tr>
          <td>회원가입일</td>
          <td>${member.regdate}</td>
        </tr>
      </tbody>
    </table>
  </body>
</html>
