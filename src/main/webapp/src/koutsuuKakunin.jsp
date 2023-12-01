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
  <form action="<%=request.getContextPath()%>/ResultKoutusuuYoukyuuAction" method="post">
    <p>
    <table class="tableM">
      <tr>
        <th>請求者</th>
        <th>ステータス</th>
        <th>利用日付１</th>
        <th>区間開始１</th>
        <th>区間終了１</th>
        <th>金額１</th>
        <th>備考１</th>
        <th>ステータス</th>
        <th>利用日付２</th>
        <th>区間開始２</th>
        <th>区間終了２</th>
        <th>金額２</th>
        <th>備考２</th>
      </tr>
      <tr>
        <td>
          <%="1:西山龍一"%>
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
        <td>
          <%="ステータス"%>
        </td>
        <td>
          <%="利用日付２"%>
        </td>
        <td>
          <%="区間開始２"%>
        </td>
        <td>
          <%="区間終了２"%>
        </td>
        <td>
          <%="金額２"%>
        </td>
        <td>
          <%="備考２"%>
        </td>
        <td>
          <%="ステータス"%>
        </td>
      </tr>
    </table>
    </p>

    <a href="/TDSLWebProject/src/koutsuuYoukyuu.jsp">交通費精算要求画面に戻る</a>
  </form>
</body>
</html>