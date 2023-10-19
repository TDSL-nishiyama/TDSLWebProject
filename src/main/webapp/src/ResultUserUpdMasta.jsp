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
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー更新画面</title>
</head>
<body>
	<%@ include file="../msg.jsp"%>
	<form name="ResultUserUpd"
		action="<%=request.getContextPath()%>/ResultUserUpdAction"
		method="post">
		更新するユーザーIDを入力してください
		<p>
			ユーザーID：<input type="text" name="userIdUpd"><br> <input
				type="submit" name="updUser" value="更新"><br>
		</p>
	</form>

<%-- 	<tr>
		<th>ID</th>
		<th>ログインID</th>
		<th>名前</th>
		<th>管理フラグ</th>
		<th>更新</th>
	</tr>
	<%
	String kanriFlgView = "";
	for (MastaEntity mastaEntity : mastaEntitylist) {
	%>
	<td><%=mastaEntity.getUserid()%></td>
	<td><%=mastaEntity.getLoginid()%></td>
	<td><%=mastaEntity.getName()%></td>
	<td>
		<%
		//管理フラグtrueの場合、〇　falseの場合、×　を表示
		if (mastaEntity.getKanriFlg() == true) {
			kanriFlgView = "〇";
		} else {
			kanriFlgView = "×";
		}
		%> <%=kanriFlgView%>
	</td>
	<td>
		<form name="userUpdate"
			action="<%=request.getContextPath()%>/ResultUserUpdAction"
			method="post">
			<input type="submit" name="userUpd" value="更新"><br>
		</form>
	</td>
	<%
	}
	%>
 --%>
	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>