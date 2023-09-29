<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ログイン画面  -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
<script>
/* function checkIDandText(){

	var id = document.login.id.value;
	var password = document.login.password.value;

    if(id == ""){
        window.alert('ログインIDを入力してください');
        return false;
    }else if(!id.match(/^[A-Za-z0-9]+$/)){
    	window.alert('IDは半角英数のみ入力可能です')
    	return false;
    }else if(password == ""){
        window.alert('パスワードを入力してください');
        return false;
    } */
}</script>
</head>

<body>

	<h1>ログインIDとパスワードを入力してください</h1>

	<form name="login" action="<%=request.getContextPath()%>/LogInAction"
		method="post">

		<p>
			ID: <input type="text" name="id">
		</p>
		<p>
			PASS:<input type="password" name="password">
		</p>

		<p>
			<input type="submit" name="login" value="ログイン" onclick="checkIDandText()">
		</p>

	</form>

</body>
</html>