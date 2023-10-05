<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ログイン画面  -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
<script type="text/javascript" src="./js/LoginCheck.js" charset="UTF-8"></script>
</head>

<body>
	<%=
	//更新メッセージの表示
	request.getAttribute("MSG")
	%>
	<h1>ログインIDとパスワードを入力してください</h1>

	<form name="login" action="<%=request.getContextPath()%>/LogInAction"
		method="post" onsubmit="checkIDandText();retrun false;">

		<p>
			ID: <input type="text" name="id">
		</p>
		<p>
			PASS:<input type="password" name="password">
		</p>

		<p>
			<input type="submit" name="login" value="ログイン">
		</p>

	</form>

	<form name="login" action="<%=request.getContextPath()%>/UpdatePasswordAction"
		method="post"">

		<p>
			<input type="submit" name="updatePassword" value="パスワード再登録">
		</p>

	</form>

</body>
</html>