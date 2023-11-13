<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  汎用システムエラー画面 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="all" href="./css/main.css">
<title>エラー</title>
</head>
<body>

<p>ERROR:<span class="altMsg"><%= request.getAttribute("ERRMSG") %></span></p>

<a href = "/TDSLWebProject/login.jsp">トップページに戻る</a>

</body>
</html>