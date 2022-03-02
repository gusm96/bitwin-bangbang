// 저장된 장바구니 목록 불러오기 (로컬스토리지/세션스토리지)
function getCartItemInfo(contextPath) {
  $.ajax({
    url: contextPath + "/cart",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(cart),
    success: function (data) {
      // 장바구니 목록 출력
      printList(data, contextPath);
    },
    error: function () {
      $("#list").html('<td colspan="7">[OC2] error</td>');
    },
  });
}

// 장바구니 목록 출력
function printList(data, contextPath) {
  let html = "";

  $("#list").html(html);

  let totalPrice = 0;
  let totalPay = 0;

  if (!jQuery.isEmptyObject(data)) {
    html += "<thead><tr><th>no</th>";
    html += "<th>사진</th>";
    html += "<th>상품명</th>";
    html += "<th>수량</th>";
    html += "<th>가격</th>";
    html += "<th>할인가격</th></tr></thead>";
    html += "<tbody>";

    for (let i = 0; i < data.length; i++) {
      html += "<tr><td>" + (i + 1) + "</td>";
      html += '<td><a href=' + contextPath + '/board/detail?iidx=' + data[i].iidx + '>' + data[i].thumb + " </td>";
      html += '<td><a href=' + contextPath + '/board/detail?iidx=' + data[i].iidx + '>' + data[i].name + "</a></td>";
      html += "<td>" + data[i].qty + "개</td>";

      let price = data[i].price * data[i].qty;
      let salePrice = data[i].salePrice * data[i].qty;

      html +=
        salePrice != 0
          ? '<td class="text-right"><p style="text-decoration: line-through">' +
            moneyEx(price) +
            "원</p></td>"
          : "<td></td>";
      html +=
        salePrice != 0
          ? '<td class="text-right">' + moneyEx(salePrice) + "원</td></tr>"
          : '<td class="text-right">' + moneyEx(price) + "원</td></tr>";

      totalPrice += price;
      totalPay += salePrice == 0 ? price : salePrice;
    }

    html += "</tbody>";
    $("#list").append(html);

    $("#totalPrice").html(moneyEx(totalPrice));
    $("#discount").html(moneyEx(totalPay - totalPrice));
    $("#point").html(moneyEx($("#point").text() - $("#pointUse").val()));
    $("#totalPay").html(moneyEx(totalPay));

    $("#item").val(data[0].name);
    $("#amount").val(totalPay);
  } else {
    $("body").html("<h1>장바구니에 담긴 상품이 없습니다.</h1>");
  }
}

// 주문 요청
function orderRequest(contextPath) {
  let orderRequest = {
    uidx: $("#uidx").val(),
    sidx: $("#sidx").val(),
    name: $("#name").val(),
    phone: $("#phone").val(),
    msg: $("#msg").val(),
    orderItems: cart,
  };

  $.ajax({
    url: "",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(orderRequest),
    success: function (data) {
      // 결제 정보 추가
      if (insertPayment(data, contextPath)) {
        let html = "";

        html += "<h1>주문이 완료되었습니다.<h1>";
        html += '<input type="button" value="주문 상세 보기" ';
        html +=
          "onclick=\"location.href='" + contextPath + "/order/member/list/";
        html += data + "'\">";

        $("body").html(html);

        // 주문상품 장바구니 비우기
        storage.removeItem(key);
      } else {
        location.href = contextPath;
      }
    },
    error: function (data) {
      $("body").html(data + "등록에 실패하였습니다. 관리자에게 문의하세요.");
    },
  });
}

// 결제 정보 추가
function insertPayment(data, contextPath) {
  let result = true;

  let paymentInfo = {
    oidx: data,
    totalprice: $("#totalPrice").text().replace(/,/g, ""),
    discount: $("#discount").text().replace(/,/g, ""),
    pointused: $("#pointUse").val() * -1,
    totalpay: $("#totalPay").text().replace(/,/g, ""),
    method: "card",
  };

  $.ajax({
    url: contextPath + "/order/pay",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(paymentInfo),
    success: function () {
      result = true;
    },
    error: function (data) {
      result = false;
      alert(data + "등록에 실패하였습니다. 관리자에게 문의하세요.");
    },
  });
  return result;
}

// 화폐 단위 (1,000,000) 표현식
function moneyEx(number) {
  return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
