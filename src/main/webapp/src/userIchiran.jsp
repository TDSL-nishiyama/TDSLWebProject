<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.UserAddEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<UserAddEntity> userAddEntityList = (List<UserAddEntity>)
request.getAttribute(Path.USER_ICHIRAN_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>ユーザー一覧</title>
</head>
<body>
	<form name="toUserAdd"
		action="<%=request.getContextPath()%>/UserAddAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザーの追加">
		</p>
	</form>
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>ログインID</th>
			<th>名前</th>
			<th>管理フラグ</th>
			<th>更新</th>
			<th>削除</th>
		</tr>

		<%
		for (UserAddEntity userAddEntity : userAddEntityList) {
		%>
		<tr>
			<td><%=userAddEntity.getUserid()%></td>
			<td><%=userAddEntity.getLoginid()	%></td>
			<td><%=userAddEntity.getName()	%></td>
			<td><%=userAddEntity.getKanriFlg()	%></td>
			<td>更新</td>
			<td>削除</td>
		</tr>
		<%
		}
		%>

	</table>
	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>
</body>
</html>