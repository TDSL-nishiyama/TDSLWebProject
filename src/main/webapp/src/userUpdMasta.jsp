<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー更新画面</title>
</head>
<body>
	<%@ include file="../msg.jsp"%>
	<form name="selectUpdateUser"
		action="<%=request.getContextPath()%>/ResultUserUpdAction"
		method="post">
		更新するユーザーIDを入力してください
		<p>
			ユーザーID：<input type="text" name="userIdUpd"><br>
			<input type="submit" name="addUser" value="更新"><br>
		</p>
		<tr>
			<th>ID</th>
			<th>ログインID</th>
			<th>名前</th>
			<th>管理フラグ</th>
			<th>更新</th>
			<th>削除</th>
		</tr>
	</form>
	
	<form name="userUpdate"
		action="<%=request.getContextPath()%>/ResultUserUpdAction"
		method="post">
		更新するユーザーIDを入力してください
		<p>
			ユーザーID：<input type="text" name="userIdUpd"><br>
			<input type="submit" name="addUser" value="更新"><br>
		</p>
	</form>

	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>