<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.MastaEntitytjava.util.Listst"%>
<%@ pageimportconstents.Const.Pathth"%>


<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaEntity> userAddEntityList = (List<MastaEntity>)
request.getAttribute(Path.USER_ICHIRAN_SCOPE)
E);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>ユーザー一覧</title>
</head>
<body>
	<form name="toUserAdd" action=<%=request.getContextPathth()%>
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
		for (MastaEntity userAddEntity : userAddEntityList) {
		%>
		<td><%=userAddEntity.getUserid()%></td>
		<td><%=userAddEntity.getLoginid()	%></td>
		<td><%=userAddEntity.getName()	%></td>
		<td><%=userAddEntity.getKanriFlg()	%></td>
		<td><form><input type="submit" name="toSakujo" value="更新"></form></td>
		<td><form><input type="submit" name="toSakujo" value="削除"></form></td>
		</tr>
		<%
		}
		%>
		<form name="toUserIchiranAll" action=<%=request.getContextPathth()%>
			/UserIchiranAction" method="post">
			<p>
				<input type="submit" name="toIchiranAll" value="削除したユーザーも含めて再表示">
			</p>
		</form>
	</table>
	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>
</body>
</html>