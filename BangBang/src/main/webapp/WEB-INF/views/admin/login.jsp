<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>관리자 로그인</title>
  </head>
  <body>
    <form method="post">
      <table>
        <tr>
          <td>관리자 아이디</td>
          <td><input type="text" name="aid" required /></td>
        </tr>
        <tr>
          <td>관리자 비밀번호</td>
          <td><input type="password" name="apw" required /></td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="로그인" /></td>
        </tr>
      </table>
    </form>
  </body>
</html>
