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
  <form action="<%=request.getContextPath()%>/ResultKoutsuuYoukyuuAction" method="post">
    <span class="req">※ 必須項目</span>
    <p>
    <table class="tableM">
      <tr>
        <th>請求者</th>
        <th>
          <span class="req">※</span>利用日付１
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
        <td><%=loginSession.getLoginName()%></td>
        <td>
          <input type="text" name="riyou1" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukans1" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukane1" maxlength="100">
        </td>
        <td>
          <input type="text" name="kingaku1" maxlength="100">
        </td>
        <td>
          <input type="text" name="bikou1" maxlength="100">
        </td>
        <td>
          <input type="text" name="riyou2" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukans2" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukane2" maxlength="100">
        </td>
        <td>
          <input type="text" name="kingaku2" maxlength="100">
        </td>
        <td>
          <input type="text" name="bikou2" maxlength="100">
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