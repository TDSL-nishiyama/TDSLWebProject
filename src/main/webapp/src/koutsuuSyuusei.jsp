<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.KoutsuuBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<KoutsuuBean> koutsuuBeanList = (List<KoutsuuBean>) request.getAttribute(Path.KOUTSUU_KAKUNIN_SCOPE);
%>

<!DOCTYPE html>
<!-- 交通費修正画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>交通費修正</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form action="<%=request.getContextPath()%>/ResultKoutsuuSyuuseiAction" method="post">
    <span class="req">※ 必須項目</span>
    <p>
    <table class="tableM">
      <tr>
        <th>請求No</th>
        <th>利用者</th>
        <th>
          <span class="req">※</span>利用日付
        </th>
        <th>
          <span class="req">※</span>区間開始
        </th>
        <th>
          <span class="req">※</span>区間終了
        </th>
        <th>
          <span class="req">※</span>金額
        </th>
        <th>備考</th>
        <th>差戻事由</th>
      </tr>
      <tr>
        <td>
          <input type="hidden" name="selNo" value="<%=koutsuuBeanList.get(0).getNo()%>">
          <%=koutsuuBeanList.get(0).getNo()%>
        </td>
        <td>
          <input type="hidden" name="selId" value="<%=koutsuuBeanList.get(0).getId()%>">
          <%=koutsuuBeanList.get(0).getUserName()%>
        </td>
        <td>
          <input type="text" name="riyouhiduke" value="<%=koutsuuBeanList.get(0).getRiyouView()%>" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukans" value="<%=koutsuuBeanList.get(0).getKukan_start()%>" maxlength="100">
        </td>
        <td>
          <input type="text" name="kukane" value="<%=koutsuuBeanList.get(0).getKukan_end()%>" maxlength="100">
        </td>
        <td>
          <input type="text" name="kingaku" value="<%=koutsuuBeanList.get(0).getKingaku()%>" maxlength="100">
        </td>
        <td>
          <input type="text" name="bikou" value="<%=koutsuuBeanList.get(0).getBikou()%>" maxlength="100">
        </td>
        <td>
          <input type="hidden" name="modoshiriyuu" value="<%=koutsuuBeanList.get(0).getModoshiriyuu()%>">
          <%=koutsuuBeanList.get(0).getModoshiriyuu()%>
        </td>
      </tr>
    </table>
    </p>
    <p>
      <input type="submit" value="修正">
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