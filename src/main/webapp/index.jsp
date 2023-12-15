<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインページ</title>
<%@ include file="./header.jsp"%>
</head>
<body>

  <form name="toSyainJouhou" action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
    <%
    //一般ユーザーの場合
    if (kanriFlg == false) {
    %>
    <p>
      <input type="submit" name="syainIpppan" value="社員名簿の閲覧">
    </p>
    <%
    //管理者の場合
    } else {
    %>
    <p>
      <input type="submit" name="syainKanrisya" value="社員名簿の閲覧（管理者用）">
    </p>
    <%}%>
  </form>

  <form name="toKoutsuuYoukyuu" action="<%=request.getContextPath()%>/KoutsuuYoukyuuAction" method="post">
    <p>
      <input type="submit" name="toMasta" value="交通費精算要求画面に遷移">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/KoutsuuKakuninAction" method="post">
    <p>
      <input type="submit" value="交通費精算確認画面に遷移">
    </p>
  </form>

  <%
  if (kanriFlg == true) {
  %>
  <form action="<%=request.getContextPath()%>/KoutsuuShouninAction" method="post">
    <p>
      <input type="submit" value="交通費精算承認画面に遷移">
    </p>
  </form>
  
  <br><br>

  <form name="toMasta" action="<%=request.getContextPath()%>/MastaAction" method="post">
    <p>
      <input type="submit" name="toMasta" value="マスタ画面に遷移">
    </p>
  </form>
  <%}%>

  <!-- 何かを実装するためのテスト用のページとしてご自由にご利用ください -->
  <br><br>
  <form name="test" action="<%=request.getContextPath()%>/TestAction" method="post">
    <p>
      <input type="submit" value="テスト用ページに遷移">
    </p>
  </form>

</body>
</html>