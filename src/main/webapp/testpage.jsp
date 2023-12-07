<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 何かを実装するためのテスト用のページとしてご自由にご利用ください -->
<html>
<head>
<meta charset="UTF-8">
<title>テスト用JSP</title>
</head>
<body>
  <%@ include file="./msg.jsp"%>
  <p>
  <%=request.getAttribute("KOUMOKU")%>：<%=request.getAttribute("TEST") %>
  </p>


  <a href="index.jsp">戻る</a>

</body>
</html>