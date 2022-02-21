<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>아이디 찾기</title>
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
        <button type="submit">인증 요청</button>
      </div>
      <div id="msg"></div>
    </form>
  </body>
</html>
