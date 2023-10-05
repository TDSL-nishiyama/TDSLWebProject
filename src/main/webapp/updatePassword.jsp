<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="constents.Const.Path"%>
<!-- パスワード登録更新画面 -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード登録更新</title>
<script type="text/javascript" src="./js/UpdatePasswordCheck.js"
	charset="UTF-8"></script>
</head>

<body>
	<%
	// 遷移元Pathの取得
	String reServlet = request.getServletPath();
	%>
	<form name="updatePassword"
		action="<%=request.getContextPath()%>/ResultUpdatePasswordAction"
		method="post" onsubmit="checkUpdatePassword();retrun false;">
		<h1>ログインID（仮登録ユーザーのみ)とパスワードを入力してください</h1>
		<%
		//遷移元がLoginAction（初回ログイン想定）の場合はユーザー名を登録させるためテキストボックスを表示
		if (reServlet.equals(Path.UPDATE_PASSWORD_GAMEN)) {
		%>
		<p>
			ログインID: <input type="text" name="loginid">
		</p>
		<%
		}
		%>
		<p>
			パスワード: <input type="password" name="pass1">
		</p>
		<p>
			パスワード（再入力）:<input type="password" name="pass2">
		</p>
		<p>
			<input type="submit" name="submitPass" value="登録">
		</p>
	</form>

</body>
</html>