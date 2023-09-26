<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div align="right">IDに紐づく人名を表示させたい</div>
<br>

<form name="btnLogout"
	action="<%=request.getContextPath()%>/LogOutAction" method="post">

	<div align="right">
		<input type="submit" name="logout" value="ログアウト">
	</div>

</form>

</html>