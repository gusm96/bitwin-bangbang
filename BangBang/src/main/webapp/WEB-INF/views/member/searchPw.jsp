<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비밀번호 찾기</title>
    <%@ include file="/WEB-INF/views/frame/pageset.jsp"%>
    <style>
      #msg {
        display: none;
      }

      .text_red {
        color: red;
      }

      .text_blue {
        color: blue;
      }
    </style>
    <script>
      $(document).ready(function () {
        $("#email").focusin(function () {
          $("#msg").css("display", "none");
          $("#msg").removeClass("text_blue");
          $("#msg").removeClass("text_red");
          $("#msg").text("");
        });

        $("#email").focusout(function () {
          if ($("email").val() == "") {
            $("#msg").css("display", "block");
            $("#msg").text("이메일을 입력하세요.");
            $("#msg").addClass("text_red");
          }
        });
      });
    </script>
  </head>
  <body>
    <form method="post">
      <div>
        <label for="email">이메일</label>
        <input
          type="email"
          name="email"
          id="email"
          placeholder="등록된 이메일을 입력하세요."
          required
        />
        <div id="msg"></div>
      </div>
      <div>
        <label for="userid">아이디</label>
        <input
          type="text"
          name="userid"
          id="userid"
          placeholder="회원의 아이디를 입력하세요."
          required
        />
      </div>
      <button type="submit">비밀번호 찾기</button>
    </form>
    <button id="back_btn">뒤로가기</button>
    <script>
      $("#back_btn").click(function () {
        history.go(-1);
      });
    </script>
  </body>
</html>
