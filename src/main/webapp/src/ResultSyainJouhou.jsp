 pageEncoding="UTF-8"%>
<%@ page import="model.syainJouhouEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//セッションスコープに保存されたユーザー情報を取得
List<syainJouhouEntity> syainJouhouEntityList = (List<syainJouhouEntity>) request.getAttribute(Path.SESSION_SCOPE_NAME);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>選択結果</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>社員ID</th>
			<th>名前</th>
			<th>読み仮名</th>
			<th>性別</th>
			<th>出身地</th>
			<th>現住所</th>
			<th>住宅手当有無</th>
			<th>職位</th>
			<th>年収</th>
			<th>入社年月</th>
			<th>勤続年数</th>
		</tr>

		<%
		for (syainJouhouEntity syainJouhouEntity : syainJouhouEntityList) {
		%>
		<tr>
			<td><%=syainJouhouEntity.getId()%></td>
			<td><%=syainJouhouEntity.getName()%></td>
			<td><%=syainJouhouEntity.getYomigana()%></td>
			<td><%=syainJouhouEntity.getSeibetsu()%></td>
			<td><%=syainJouhouEntity.getSyussinn()%></td>
			<td><%=syainJouhouEntity.getJuusyo()%></td>
			<td><%=syainJouhouEntity.getJutakuTeateUmu()%></td>
			<td><%=syainJouhouEntity.getShokui()%></td>
			<td><%=syainJouhouEntity.getSalary()%></td>
			<td><%=syainJouhouEntity.getNyuusyaNengetsu()%></td>
			<td><%=syainJouhouEntity.getKinzokuNennsuu()%></td>
		</tr>
		<%
		}
		%>

	</table>

	<p>
		<a href="/TDSLWebProject/index.jsp">戻る</a>
	</p>
</body>
</html>