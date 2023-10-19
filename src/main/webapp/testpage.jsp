<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト用JSP</title>
</head>
<body>

	<form name="login" action="<%=request.getContextPath()%>/ResultUserIchiranAction"
		method="post"">
		<p>
			<input type="submit" name="toResultUserIchiran"
				value="削除されたユーザーも含めて表示">
		</p>
	</form>



<a href="index.jsp">戻る</a>

</body>
</html>