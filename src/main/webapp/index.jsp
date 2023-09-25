<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>

	<form name="login" action="<%=request.getContextPath()%>/SyainJouhouAction"
		method="post" onSubmit="return checkIDandText()">
		<p>
			<input type="submit" name="login" value="社員名簿の閲覧">
			<input type="submit" name="login" value="社員名簿の閲覧（管理者用）">
		</p>

	</form>

</body>
</html>