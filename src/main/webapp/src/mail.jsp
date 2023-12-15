<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.MastaBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaBean> mastaBeanList = (List<MastaBean>) request.getAttribute(Path.MAIL_SCOPE);
String txtUserId = "";
if (request.getAttribute("selId") != null) {
  txtUserId = (String) request.getAttribute("selId");
}
String txtMailAddress = "";
if (request.getAttribute("mailAddress") != null) {
  txtMailAddress = (String) request.getAttribute("mailAddress");
}
%>

<!DOCTYPE html>
<!-- メールアドレス登録更新画面ホーム  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>メールアドレス登録更新</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <p>
    <span class="req">※必須項目</span>
  <form action="<%=request.getContextPath()%>/MailAddAction" method="post">
    <p>
      <input type="submit" value="メールアドレスの新規追加">
    </p>
    <span class="req">※</span>追加する社員ID:
    <input type="text" name="selId" value="<%=txtUserId%>">
    <br> <span class="req">※</span>追加するメールアドレス:
    <input type="text" name="mailAddress" value="<%=txtMailAddress%>">
    <br>
  </form>
  </p>

  <p>
    <strong>メールアドレス一覧</strong>
  <table class="tableM">
    <tr>
      <th>ID</th>
      <th>ユーザー名</th>
      <th>メールアドレス</th>
      <th>操作</th>
    </tr>
    <%
    for (MastaBean bean : mastaBeanList) {
    %>
    <tr>
      <td>
        <%=bean.getUserid()%>
      </td>
      <td>
        <%=bean.getUserName()%>
      </td>
      <td>
        <%=bean.getMailAddress()%>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/MailUpdAction" method="post">
          <input type="hidden" name="selId" value="<%=bean.getUserid()%>">
          <input type="hidden" name="mailAddress" value="<%=bean.getMailAddress()%>">
          <input type="submit" value="更新">
        </form>
        <form action="<%=request.getContextPath()%>/MailDelAction" method="post">
          <input type="hidden" name="selId" value="<%=bean.getUserid()%>">
          <input type="hidden" name="mailAddress" value="<%=bean.getMailAddress()%>">
          <input type="submit" value="削除">
        </form>
      </td>
    </tr>
    <%}%>
  </table>
  </p>

  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>
</body>
</html>