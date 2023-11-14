<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.MastaEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaEntity> mastaEntitylist = (List<MastaEntity>) request.getAttribute(Path.USER_SELECT_HENSYU);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー更新実行画面</title>
</head>
<body>
	<%@ include file="../msg.jsp"%>
	<form name="userKoushinJikkou" action="<%=request.getContextPath()%>/ResultUserUpdAction"
		method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>管理フラグ</th>
				<th>更新</th>
			</tr>
			<%
			for (MastaEntity mastaEntity : mastaEntitylist) {
			%>
			<td><%=mastaEntity.getUserid()%></td>
			<td><input type=text" name="username" value="<%=mastaEntity.getName()%>"</td>
			<td>
			<%
			//mastaEntityから選択ユーザーの管理者権限を取得してcheckedを制御
			if(mastaEntity.getKanriFlg() == false){
			%>
				管理者権限:
				あり：<input type="radio" name="kanriflg" value=1>
				なし：<input type="radio" name="kanriflg" value=0 checked>
			<%
			}else{
			%>
				管理者権限:
				あり：<input type="radio" name="kanriflg" value=1 checked>
				なし：<input type="radio" name="kanriflg" value=0>
			<%
			}
			%>
			</td>
			<td><input type="submit" name="userUpd" value="更新"><br>
			</td>
			<input type="hidden" name="hdnUserId" value=<%=mastaEntity.getUserid()%>>
			<%
			}
			%>
		</table>
	</form>
	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>