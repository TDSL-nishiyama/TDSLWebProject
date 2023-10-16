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
	<form name="userUpdate"
		action="<%=request.getContextPath()%>/ResultUserUpdAction"
		method="post">
		<p>
			ログインID<%=loginSession.getLoginId()%><br> ユーザー名：<input
				type="text" name="userName"><br> 管理者権限<br> <input
				type="radio" name="kanriFlg" value="1"> あり<br> <input
				type="radio" name="kanriFlg" value="0" checked> なし<br>
			<br> <input type="submit" name="addUser" value="登録内容変更"><br>
		</p>
	</form>

	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>