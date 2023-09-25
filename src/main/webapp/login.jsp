<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ログイン画面  -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link src="./js/LoginCheck.js">
<title>ログイン画面</title>
</head>

<body>

	<h1>ログインIDとパスワードを入力してください</h1>

	<form name="login" action="<%=request.getContextPath()%>/IndexAction"
		method="post" onSubmit="return checkIDandText()">

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

</body>
</html>