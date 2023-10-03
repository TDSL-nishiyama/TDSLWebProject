<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- パスワード登録更新画面 -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード登録更新</title>
<script type="text/javascript" src="./js/UpdatePasswordCheck.js" charset="UTF-8"></script>
</head>

<body>

<%
//TODO ログインIDの取得
//TODO 初回ログインの場合はログインIDがない
%>
	<form name="updatePassword" action="<%=request.getContextPath()%>/ResultUpdatePasswordAction"
		method="post" onsubmit="checkUpdatePassword();retrun false;">
		<h1>パスワードを入力してください</h1>
		<p>
			パスワード: <input type="password" name="pass1">
		</p>
		<p>
			パスワード（再入力）:<input type="password" name="pass2">
		</p>

		<p>
			<input type="submit" name="submitPass" value="パスワード登録">
		</p>
	</form>

</body>
</html>