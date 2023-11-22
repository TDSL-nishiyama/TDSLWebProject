<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー削除画面</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form name="userDelete"
    action="<%=request.getContextPath()%>/ResultUserDelAction"
    method="post">
    削除するユーザーIDを入力してください
    <p>
      ユーザーID：<input type="text" name="userIdDel">
      <input type="submit" name="userDel" value="削除">
    </p>
    <%//TODO ユーザー削除の際に退社年月日をユーザー詳細テーブルに挿入 %>
    
  </form>

  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>

</body>
</html>