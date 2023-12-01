<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 交通費精算要求画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>交通費精算</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form action="<%=request.getContextPath()%>/ResultKoutusuuYoukyuuAction" method="post">
    <span class="req">※ 必須項目</span>
    <p>
    <table class="tableM">
      <tr>
        <th>請求者</th>
        <th>
          <span calss="req">※</span>利用日付１
        </th>
        <th>
          <span class="req">※</span>区間開始１
        </th>
        <th>
          <span class="req">※</span>区間終了１
        </th>
        <th>
          <span class="req">※</span>金額１
        </th>
        <th>備考１</th>
        <th>利用日付２</th>
        <th>区間開始２</th>
        <th>区間終了２</th>
        <th>金額２</th>
        <th>備考２</th>
      </tr>
      <tr>
        <td><%="1:西山龍一" %></td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
        <td>
          <input type="text" value="" maxlength="100">
        </td>
      </tr>
    </table>
    </p>
    <p>
      <input type="submit" value="請求">
    </p>
  </form>
  <p>
  <form action="<%=request.getContextPath()%>/KoutsuuKakuninAction" method="post">
    <p>
      <input type="submit" value="交通費精算確認画面に遷移">
    </p>
  </form>
  </p>

  <a href="/TDSLWebProject/index.jsp">トップページに戻る</a>
</body>
</html>