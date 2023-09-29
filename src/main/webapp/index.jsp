<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインページ</title>
<%@ include file="./header.jsp"%>
</head>
<body>

	<form name="toSyainmeibo"
		action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
		<p>
			<input type="submit" name="syainIpppan" value="社員名簿の閲覧">
		</p>
		<% if(kanriFlg == true){%>
		<p>
			<input type="submit" name="syainKanrisya" value="社員名簿の閲覧（管理者用）">
		</p>
		<%}%>
	</form>
	
	<% if(kanriFlg == true){%>
	<form name="toMasta"
		action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="マスタ画面に遷移">
		</p>
	</form>
	<%}%>
	
	<form name="test"
		action="<%=request.getContextPath()%>/TestAction" method="post">
		<p>
			<input type="submit" name="login" value="テスト用ページに遷移">
		</p>
	</form>

</body>
</html>