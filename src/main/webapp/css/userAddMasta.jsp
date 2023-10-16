<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="/js/UserAddCheck.js" charset="UTF-8"></script>
<title>ユーザー登録画面</title>
</head>
<body>
	<%@ include file="../msg.jsp"%>
	<form name="userInsert"
		action="<%=request.getContextPath()%>/ResultUserAddAction" method="post">
		<p>
			ユーザー名：<input type="text" name="userName"><br>
			管理者権限<br>
			<input type="radio" name="kanriFlg" value="1"> あり<br>
			<input type="radio" name="kanriFlg" value="0" checked> なし<br><br>
			<input type="submit" name="addUser" value="ユーザー登録"><br>
		</p>
	</form>
	
	<p>
		<a href="/TDSLWebProject/masta.jsp">戻る</a>
	</p>

</body>
</html>