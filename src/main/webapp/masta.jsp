<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="./header.jsp"%>
<title>マスタ画面</title>
</head>
<body>
  <table>
    <th></th>
  </table>
  <form action="<%=request.getContextPath()%>/UserIchiranAction" method="post">
    <p>
      <input type="submit" value="ユーザー一覧">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/UserAddAction" method="post">
    <p>
      <input type="submit" value="ユーザー登録">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/UserUpdAction" method="post">
    <p>
      <input type="submit" value="ユーザー更新">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/UserDelAction" method="post">
    <p>
      <input type="submit" value="ユーザー削除">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/KaritourokuAction" method="post">
    <p>
      <input type="submit" value="仮登録ユーザー一覧">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/MailMastaAction" method="post">
    <p>
      <input type="submit" value="メールアドレス一覧">
    </p>
  </form>

  <a href="/TDSLWebProject/index.jsp">戻る</a>

</body>
</html>