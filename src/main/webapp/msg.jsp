<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--更新メッセージ表示クラス-->
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" media="all" href="./css/main.css">
</head>
<body>
  <%
  //リクエストスコープに保存したメッセージを出力する際に属性がないとNULLが出力されるためブランクを代入
  String msg = "";
  msg = (String)request.getAttribute("MSG");
  if(msg == null){
    msg = "";
  }
  %>
  <div class="altMsg">
  <%=
  //更新メッセージの表示
  msg
  %>
  </div>
</body>
</html>