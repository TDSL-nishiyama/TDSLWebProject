<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="constents.Const.Path"%>
<%@ page import="model.SessionKanriBean" %>
<%@ page import="jakarta.servlet.http.HttpServlet,
jakarta.servlet.http.HttpServletRequest,
jakarta.servlet.http.HttpServletResponse,
jakarta.servlet.RequestDispatcher"
%>

<%
//セッションスコープに保存されたユーザー情報を取得
SessionKanriBean loginSession = (SessionKanriBean)session.getAttribute(Path.SESSION_SCOPE_NAME);
//ログイン確認
if (loginSession == null) {
  // ログインしていない場合ログイン画面に遷移
  RequestDispatcher dispatcher = request.getRequestDispatcher(Path.LOGIN_GAMEN);
  dispatcher.forward(request, response);
  return;
}
  //管理フラグの設定
  boolean kanriFlg = loginSession.getKanriFlg();
%>

<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div align="right">
  ログインユーザー：<%=loginSession.getLoginName()%></div>
<br>

<form name="btnLogout"
  action="<%=request.getContextPath()%>/LogOutAction" method="post">

  <div align="right">
    <input type="submit" name="logout" value="ログアウト">
  </div>
</form>

</html>