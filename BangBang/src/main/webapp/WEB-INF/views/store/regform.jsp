<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 등록</title>
  </head>
  <body>
    <form method="post">
      <table>
        <tr>
          <td>아이디</td>
          <td><input type="text" name="storeid" id="storeid" required /></td>
        </tr>
        <tr>
          <td>비밀번호</td>
          <td>
            <input type="password" name="storepw" id="storepw" required />
          </td>
        </tr>
        <tr>
          <td>비밀번호 확인</td>
          <td>
            <input type="password" name="storepw2" id="storepw2" required />
            <div id="pw2"></div>
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
          <td>사업자 명의</td>
          <td><input type="text" name="oname" id="oname" required /></td>
        </tr>
        <tr>
          <td>사업자 전화번호</td>
          <td><input type="text" name="ophone" id="ophone" required /></td>
        </tr>
        <tr>
          <td>사업자 등록번호</td>
          <td><input type="text" name="regnum" id="regnum" required /></td>
        </tr>
      </table>
    </form>
  </body>
</html>
