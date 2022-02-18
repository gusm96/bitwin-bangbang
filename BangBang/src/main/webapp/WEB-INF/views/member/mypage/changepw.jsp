<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비밀번호 변경</title>
    <%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
    <script type="text/javascript" src="<c:url value="/resources/js/checkpw.js"/>"></script>
    <script>
      $(document).submit(function (e) {
        e.preventDefault();
        const regex =
          /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
        if (!regex.test($("#password1"))) {
          alert("비밀번호는 영문, 숫자, 기호를 사용하여 8자 이상 입력하세요. ");
          return false;
        }
        return true;
      });
    </script>
  </head>
  <body>
    <form method="post" id="pwform">
      <h3>비밀번호 변경</h3>
      <div>
        <label for="current_pw">현재 비밀번호</label>
        <input
          type="password"
          name="currentPw"
          id="current_pw"
          placeholder="현재 비밀번호를 입력하세요."
          required
        />
      </div>
      <div>
        <label for="new_password1">새 비밀번호</label>
        <input
          type="password"
          name="newPw1"
          id="password1"
          placeholder="영문, 숫자, 기호를 사용하여 8자 이상 입력하세요."
          min="8"
          max="16"
          required
        />
      </div>
      <div>
        <label for="new_password2">새 비밀번호 확인</label>
        <input
          type="password"
          name="newPw2"
          id="password2"
          placeholder="새로운 비밀번호를 확인하세요."
          min="8"
          max="16"
          required
        />
      </div>
      <div id="pmsg"></div>
      <button type="submit" id="submit_btn">수정하기</button>
    </form>
  </body>
</html>
