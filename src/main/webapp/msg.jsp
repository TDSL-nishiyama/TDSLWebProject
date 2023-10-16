<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
	//リクエストスコープに保存したメッセージを出力する際に属性がないとNULLが出力されるためブランクを代入
	String msg = "";
	msg = (String)request.getAttribute("MSG");
	if(msg == null){
		msg = "";
	}
	%>
	<%=
	//更新メッセージの表示
	msg
	%>
</body>
</html>