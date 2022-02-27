<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 정보 수정</title>
  </head>
  <body>
    <form method="post">
      <table>
        <tr>
          <td>가맹점명</td>
          <td>
            <input type="text" name="sname" value="${store.sname" required />
          </td>
        </tr>
        <tr>
          <td>가맹점 번호</td>
          <td>
            <input
              type="text"
              name="sphone"
              value="${store.sphone}"
              required
            />/td>
          </td>
        </tr>
        <tr>
          <td>가맹점 주소</td>
          <td>
            <input
              type="text"
              name="address"
              value="${store.address}"
              required
            />
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>
