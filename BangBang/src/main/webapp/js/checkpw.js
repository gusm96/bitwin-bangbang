$(document).ready(function () {
  $("#password2").focusin(function () {
    $("#pw2").css("display", "none");
    $("#pw2").removeClass("text_blue");
    $("#pw2").removeClass("text_red");
    $("#pw2").text("");
  });
  $("#password2").focusout(function () {
    var pwd1 = $("#password").val();
    var pwd2 = $("#password2").val();

    if (pwd1 != "" && pwd2 == "") {
      null;
    } else if (pwd1 != "" || pwd2 != "") {
      if (pwd1 == pwd2) {
        // 비밀번호 일치 이벤트 실행
        $("#pw2").css("display", "block");
        $("#pw2").text("비밀번호가 일치합니다.");
        $("#pw2").addClass("text_blue");
      } else {
        // 비밀번호 불일치 이벤트 실행
        $("#pw2").css("display", "block");
        $("#pw2").text("비밀번호가 일치하지 않습니다.");
        $("#pw2").addClass("text_red");
      }
    }
  });
});
