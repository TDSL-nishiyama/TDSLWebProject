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
	<table>
	<th>
	</th>
	</table>
	<form name="toUserIchiran"
		action="<%=request.getContextPath()%>/UserIchiranAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザー一覧">
		</p>
	</form>

	<form name="toUserAdd"
		action="<%=request.getContextPath()%>/UserAddAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザー登録">
		</p>
	</form>
	
	<form name="toUserUpdate"
		action="<%=request.getContextPath()%>/UserUpdAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザー更新">
		</p>
	</form>
	
	<form name="toUserDelete"
		action="<%=request.getContextPath()%>/UserDelAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザー削除">
		</p>
	</form>
	
	<a href = "/TDSLWebProject/index.jsp">戻る</a>
	
</body>
</html>