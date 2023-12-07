<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 交通費精算確認画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>交通費精算確認</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <p>
  <table class="tableM">
    <tr>
      <th>請求No</th>
      <th>請求者</th>
      <th>ステータス</th>
      <th>利用日付</th>
      <th>区間開始</th>
      <th>区間終了</th>
      <th>金額</th>
      <th>備考</th>
    </tr>
    <tr>
      <td>
        <%=1%>
      </td>
      <td>
        <%="1:西山龍一"%>
      </td>
      <td>
        <%="確認中"%>
      </td>
      <td>
        <%="利用日付１"%>
      </td>
      <td>
        <%="区間開始１"%>
      </td>
      <td>
        <%="区間終了１"%>
      </td>
      <td>
        <%="金額１"%>
      </td>
      <td>
        <%="備考１"%>
      </td>
    </tr>
  </table>
  </p>

  <form name="toKoutsuuYoukyuu" action="<%=request.getContextPath()%>/KoutsuuYoukyuuAction" method="post">
    <p>
      <input type="submit" name="toMasta" value="交通費精算要求画面に遷移">
    </p>
  </form>
</body>
</html>