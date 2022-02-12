$(document).ready(function () {
  $("#pw2").focusin(function () {
    $("#pmsg").css("display", "none");
    $("#pmsg").removeClass("text_blue");
    $("#pmsg").removeClass("text_red");
    $("#pmsg").text("");
  });

  $("#pw2").focusout(function () {
    $.ajax({
      url: "general/checkid",
      type: "get",
      data: {
        password: $("#pw2").val(),
      },
      success: function (data) {
        console.log("통신 결과 : ", data);

        // Y | N
        if (data == "Y") {
          $("#msg").css("display", "block");
          $("#msg").text("비밀번호가 일치 합니다.");
          $("#msg").addClass("text_blue");
        } else {
          $("#msg").css("display", "block");
          $("#msg").text("비밀번호가 일치하지 않습니다.");
          $("#msg").addClass("text_red");
        }
      },
      error: function () {
        console.log("비동기 통신 오류");
      },
    });
  });
});
