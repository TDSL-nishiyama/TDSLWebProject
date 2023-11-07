<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouEntity> syainJouhouEntityList = (List<SyainJouhouEntity>) request.getAttribute(Path.SYAIN_JOUHOU_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>

<body>
	<%
	if (kanriFlg == false) {
	%>
	<!-- 一般ユーザー -->
	<table border="1">
		<tr>
			<th>社員ID</th>
			<th>名前</th>
			<th>入社年月日</th>
			<th>出身地</th>
		</tr>

		<%
		for (SyainJouhouEntity syainJouhouEntity : syainJouhouEntityList) {
		%>
		<tr>
			<td><input type="text" value="<%=syainJouhouEntity.getId()%>"></td>
			<td><input type="text" value="<%=syainJouhouEntity.getSei()%><%=syainJouhouEntity.getMei()%>"></td>
			<td><input type="text" value="<%=syainJouhouEntity.getNyuusyaYMD()%>"></td>
			<td><input type="text" value="<%=syainJouhouEntity.getSyusshin()%>"></td>
		</tr>
		<%
		}
		%>

	</table>
	<%
	} else {
	%>
	<!-- 管理者 -->
	<table border="1">
		<tr>
			<th>社員ID</th>
			<th>名前</th>
		</tr>

		<%
		for (SyainJouhouEntity syainJouhouEntity : syainJouhouEntityList) {
		%>
		<tr>
			<td><%=syainJouhouEntity.getId()%></td>
			<td><%=syainJouhouEntity.getSei()%><%=syainJouhouEntity.getMei()%></td>
		</tr>
		<%
		}
		%>

	</table>
	<%
	}
	%>
	<p>
	
	<form name="toSJHen-SJInfo"
		action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
		<p>
			<input type="submit" name="toSJHen-SJInfo" value="戻る">
		</p>

	</form>


</body>
</html>