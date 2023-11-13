<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="constents.Const.Path"%>
<%
// セッションスコープの作成
HttpSession httpsession = request.getSession();
if (request.getAttribute(Path.BEFORE_UPDATEPASSWORD) == "LoginAction") {
  httpsession.setAttribute("USER_ATTRIBUTE", "1");
  httpsession.setAttribute("LOGINID_BEFORE", (String) request.getAttribute("LOGINID_BEFORE"));
} else {
  httpsession.setAttribute("USER_ATTRIBUTE", "0");
}
%>
<!-- パスワード登録更新画面 -->
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード登録更新</title>
<link rel="stylesheet" type="text/css" media="all" href="./css/main.css">
<script type="text/javascript" src="./js/UpdatePasswordCheck.js" charset="UTF-8"></script>
</head>

<body>
  <form name="updatePassword"
    action="<%=request.getContextPath()%>/ResultUpdatePasswordAction"
    method="post" onsubmit="checkUpdatePassword();retrun false;">
    <h1>ログインIDとパスワードを入力してください</h1>
    <div class="req">※仮登録ユーザーの場合、ログインIDを変更してください。
    <p>
      ログインID: <input type="text" name="loginid">
    </p>
    <p>
      パスワード: <input type="password" name="pass1">
    </p>
    <p>
      パスワード（再入力）:<input type="password" name="pass2">
    </p>
    <p>
      <input type="submit" name="submitPass" value="登録">
    </p>
  </form>

</body>
</html>