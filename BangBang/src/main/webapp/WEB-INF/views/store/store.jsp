<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>가맹점 정보</title>
  </head>
  <body>
    <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
    <div style="display: flex">
      <div>
        <h3>${storeInfo.sname}</h3>
        <img
          src="${pageContext.request.contextPath}/resources/uploadfile/${storeInfo.photo}"
          style="width: 50px; height: 50px"
        />
      </div>
      <div>
        <nav>
          <ul>
            <li>
              <a href="${pageContext.request.contextPath}/store/mypage"
                >가맹점 상세정보</a
              >
            </li>
          </ul>
          <ul>
            <li>
              <a href="${pageContext.request.contextPath}/store/fee"
                >가맹점 수수료 보기</a
              >
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </body>
</html>
