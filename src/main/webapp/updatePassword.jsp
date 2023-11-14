<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="constents.Const.Path,constents.Const.Common"%>
<%
// セッションスコープの作成
HttpSession httpsession = request.getSession();
//LoginActionから遷移して来た場合、新規ユーザー
if (request.getAttribute(Path.BEFORE_ACTION) == "LoginAction") {
  httpsession.setAttribute(Path.USER_ATTRIBUTE, Common.SHINKI);
  httpsession.setAttribute(Path.BEFORE_LOGIN, (String) request.getAttribute(Path.BEFORE_LOGIN));
} else {
  httpsession.setAttribute(Path.USER_ATTRIBUTE, Common.KISON);
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
    method="post" onsubmit="return checkUpdatePassword();">
    <h1>ログインIDとパスワードを入力してください</h1>
    <div class="req">※仮登録ユーザーの場合、ログインIDを変更してください。</div>
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
      <input type="submit" value="登録">
    </p>
  </form>

</body>
</html>