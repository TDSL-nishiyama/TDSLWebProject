<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト用JSP</title>
</head>
<body>

	<p>
	ユーザー数：<%=request.getAttribute("TEST") %>
	</p>


	<a href="index.jsp">戻る</a>

</body>
</html>