$("#submit").submit(function (e) {
  if ($("#userid").val().length == 0 && $("#userid").val().length < 5) {
 	 e.preventDefault();
    alert("아이디는 5글자 이상 작성해주세요.");
    $("#userid").focus();
    return false;
  }
  return true;
});
