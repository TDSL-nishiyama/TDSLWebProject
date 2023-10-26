<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouEntity> syainJouhouEntityList = (List<SyainJouhouEntity>)
request.getAttribute(Path.SYAIN_JOUHOU_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>
<body>
	<%if(kanriFlg == false){%>
	<!-- 一般ユーザー -->
	<table border="1">
		<tr>
			<th>名前</th>
		</tr>

		<%
		for (SyainJouhouEntity syainJouhouEntity : syainJouhouEntityList) {
		%>
		<tr>
			<td><%=syainJouhouEntity.getSei()%><%=syainJouhouEntity.getMei()%></td>
		</tr>
		<%
		}
		%>

	</table>
	<%}else{%>
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
	<%}%>
	<p>
		<a href="/TDSLWebProject/index.jsp">戻る</a>
	</p>
</body>
</html>