<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 로그인</title>
  </head>
  <body>
    <div>
      <form method="post">
        <table style="text-align: center">
          <thead>
            <tr>
              <th colspan="2">가맹점 로그인</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>아이디</td>
              <td>
                <input
                  type="text"
                  name="storeId"
                  id="storeId"
                  required
                  placeholder="아이디를 입력하세요."
                />
              </td>
            </tr>
            <tr>
              <td>비밀번호</td>
              <td>
                <input
                  type="password"
                  name="storePw"
                  id="storePw"
                  required
                  placeholder="비밀번호를 입력하세요."
                />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="2"><button type="submit">로그인</button></td>
            </tr>
          </tfoot>
        </table>
      </form>
    </div>
  </body>
</html>
