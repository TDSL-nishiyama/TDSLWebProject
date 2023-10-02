<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="./header.jsp"%>
<title>マスタ画面</title>
</head>
<body>
	<form name="toUserAdd"
		action="<%=request.getContextPath()%>/UserAddAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザー登録画面に遷移">
		</p>
	</form>
</body>
</html>