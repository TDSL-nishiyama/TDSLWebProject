<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.MastaEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaEntity> mastaEntitylist = (List<MastaEntity>) request.getAttribute(Path.USER_ICHIRAN_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>ユーザー一覧</title>
</head>
<body>
	<form name="toUserAdd" action=<%=request.getContextPath()%>
		/UserAddAction" method="post">
		<p>
			<input type="submit" name="toMasta" value="ユーザーの追加">
		</p>
	</form>

	<!-- 11人目以降は2ページ目  -->
	<!-- 最大表示数は100人 -->
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
		for (MastaEntity mastaEntity : mastaEntitylist) {
		%>
		<td><%=mastaEntity.getUserid()%></td>
		<td><%=mastaEntity.getLoginid()%></td>
		<td><%=mastaEntity.getName()%></td>
		<td><%=mastaEntity.getKanriFlg()%></td>
		<td><form>
				<input type="submit" name="toSakujo" value="更新">
			</form></td>
		<td><form>
				<input type="submit" name="toSakujo" value="削除">
			</form></td>
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