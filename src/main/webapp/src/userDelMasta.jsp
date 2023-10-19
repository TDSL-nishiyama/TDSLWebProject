<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー削除画面</title>
</head>
<body>
	<%@ include file="../msg.jsp"%>
	<form name="userDelete"
		action="<%=request.getContextPath()%>/ResultUserDelAction"
		method="post">
		削除するユーザーIDを入力してください
		<p>
			ユーザーID：<input type="text" name="userIdDel"><br>
			<input type="submit" name="userDel" value="削除"><br>
		</p>
	</form>

	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>