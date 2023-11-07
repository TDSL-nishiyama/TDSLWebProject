<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouBean> syainJouhouBeanList = (List<SyainJouhouBean>)
request.getAttribute(Path.SYAIN_JOUHOU_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>
<body>
	
	<form action=<%=request.getContextPath()%>/SyainJouhouHensyuAction method="post">
		<p>
			<input type="text" name="chgUserId">
			<input type="submit" name="toSyainHensyu" value="情報の編集">
		</p>
	</form>
	
	<%if(kanriFlg == false){%>
	<!-- 一般ユーザー -->
	<table border="1">
		<tr>
			<th>社員ID</th>
			<th>名前</th>
			<th>入社年月日</th>
			<th>出身地</th>
		</tr>

		<%
		for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
		%>
		<tr>
			<td><%=syainJouhouBean.getId()%></td>
			<td><%=syainJouhouBean.getName()%></td>
			<td><%=syainJouhouBean.getNyuusyaYMD()%></td>
			<td><%=syainJouhouBean.getSyusshin()%></td>
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
			<th>入社年月日</th>
			<th>年次</th>
			<th>出身地</th>
		</tr>

		<%
		for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
		%>
		<tr>
			<td><%=syainJouhouBean.getId()%></td>
			<td><%=syainJouhouBean.getName()%></td>
			<td><%=syainJouhouBean.getNyuusyaYMD()%></td>
			<td><%=syainJouhouBean.getNenji()%></td>
			<td><%=syainJouhouBean.getSyusshin()%></td>
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