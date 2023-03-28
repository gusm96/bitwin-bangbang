$(document).ready(function () {
    $("#password1").focusin(function () {
        $(".pwStrength").css("display", "none");
        $(".pwStrength").text("");
    });
    $("#password1").focusout(function () {
        if ($("#password1").val() == "") {
            $(".pwStrength").css("display", "block");
            $(".pwStrength").text("사용할 비밀번호를 작성해주세요.");
            $(".pwStrength").addClass("text_red");
        } else {
            $.ajax({
                url: "general/checkpw",
                type: "get",
                data: {
                    password: $("#password1").val(),
                },
                success: function (data) {
                        $(".pwStrength").css("display", "block");
                        $(".pwStrength").text("비밀번호 강도 "+data);
                },
                error: function () {
                    console.log("비동기 통신 오류");
                },
            });
        }
    });
})

