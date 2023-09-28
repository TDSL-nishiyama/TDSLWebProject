<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  汎用システムエラー画面 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
</head>
<body>

<p>ERROR:<%= request.getAttribute("ERRMSG") %></p>

<a href = "/TDSLWebProject/login.jsp">戻る</a>


</body>
</html>