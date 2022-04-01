<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 정보 수정 요청</title>
    <link rel="stylesheet" href="/bangbang/resources/css/container.css" />
    <style type="text/css">
      #main_container {
        padding: 20px 20px;
      }
    </style>
  </head>
  <body>
    <%@include file="../../includes/admin-header.jsp"%> <%@include
    file="../../includes/admin-nav.jsp"%>
    <div id="body_container">
      <div id="main_container" class="text-center">
        <h3>가맹점 정보 수정 요청</h3>
        <hr />
        <form method="post">
          <input type="hidden" name="oemail" value="${store.curInfo.oemail}" />
          <input type="hidden" name="sidx" value="${store.sidx}" />
          <table class="table" style="text-align: left">
            <thead>
              <tr>
                <th class="col">#</th>
                <th class="col">기존 정보</th>
                <th class="col">수정 정보</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th>가맹점명</th>
                <td>${store.curInfo.sname}</td>
                <td>
                  <span>${store.sname}</span>
                  <input
                    type="hidden"
                    name="sname"
                    value="${store.sname}"
                    required
                  />
                </td>
              </tr>
              <tr>
                <th>가맹점 번호</th>
                <td>${store.curInfo.sphone}</td>
                <td>
                  <span>${store.sphone}</span>
                  <input
                    type="hidden"
                    name="sphone"
                    value="${store.sphone}"
                    required
                  />
                </td>
              </tr>
              <tr>
                <th>가맹점 주소</th>
                <td>${store.curInfo.address}</td>
                <td>
                  <span>${store.address}</span>
                  <input
                    type="hidden"
                    name="address"
                    value="${store.address}"
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button id="cancel_btn" type="button">거절</button>
          <button id="edit_btn" type="submit" style="display: inline">
            수정
          </button>
        </form>
      </div>
    </div>
    <%@include file="../../includes/bangbang-footer.jsp"%>
    <script>
      $("#cancel_btn").click(function () {
        var reason = prompt("거절 사유를 입력해주세요.");
        $.ajax({
        	url: "${pageContext.request.contextPath}/admin/store/cancel",
          type: "post",
          data: {
        	  sridx: `${store.sridx}`,
            text: reason,
          },
          success: function (data) {

            console.log("통신결과 : ", data);

            if(data == "Y"){
         	alert("요청을 거절하였습니다.");
      location.href = "${pageConext.request.contextPath}/admin/store";
            } else {
            	alert("실패하였습니다.");
            	location.reload();
            }
          },
        });
      });
    </script>
  </body>
</html>
