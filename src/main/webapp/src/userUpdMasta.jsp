<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー更新画面</title>
</head>
<body>
  <form name="toUserIchiran" action="<%=request.getContextPath()%>/UserIchiranAction" method="post">
    <p>
      <input type="submit" name="toMasta" value="ユーザー一覧">
    </p>
  </form>
  <%@ include file="../msg.jsp"%>
  <form action="<%=request.getContextPath()%>/ResultUserUpdAction" method="post">
    更新するユーザーIDを入力してください
    <p>
      ユーザーID：
      <input type="text" name="userIdUpd">
      <input type="submit" value="更新">
      <br>
    </p>
  </form>
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>

</body>
</html>