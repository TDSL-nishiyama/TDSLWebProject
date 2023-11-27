<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ログイン画面  -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="all" href="./css/main.css">
<title>ログイン画面</title>
<script type="text/javascript" src="./js/LoginCheck.js" charset="UTF-8"></script>
</head>

<body>
 <%@ include file="./msg.jsp"%>
 <h1>ログインIDとパスワードを入力してください</h1>

 <table class="table-login">
  <form name="login" action="<%=request.getContextPath()%>/LogInAction" method="post" onsubmit="return checkIDandText();">
   <tr>
    <td>ID:</td>
    <td><input type="text" name="id"></td>
   </tr>
   <tr>
    <td>PASS:</td>
    <td><input type="password" name="password"></td>
   </tr>
   <tr>
    <td><input type="submit" name="login" value="ログイン"></td>
   </tr>
  </form>
  <form name="rePassword" action="<%=request.getContextPath()%>/UpdatePasswordAction" method="post">
  <tr>
    <td><input type="submit" name="updatePassword" value="パスワード再登録"></td>
  </tr>
  </form>
 </table>

</body>
</html>