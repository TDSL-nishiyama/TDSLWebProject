<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.MastaBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaBean> mastaBeanList = (List<MastaBean>) request.getAttribute(Path.MAIL_SCOPE);
%>

<!DOCTYPE html>
<!-- メールアドレス更新画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>メールアドレス更新</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <p>
  <form action="<%=request.getContextPath()%>/ResultMailUpdAction" method="post">
    <table class="tableM">
      <tr>
        <th>ID</th>
        <th>ユーザー名</th>
        <th>メールアドレス</th>
      </tr>
      <%
      for (MastaBean bean : mastaBeanList) {
      %>
      <tr>
        <td>
          <input type="hidden" name="selId" value="<%=bean.getUserid()%>">
          <%=bean.getUserid()%>
        </td>
        <td>
          <%=bean.getUserName()%>
        </td>
        <td>
          <input type="hidden" name="beforeMailAddress" value="<%=bean.getMailAddress()%>">
          <input type="text" name="afterMailAddress" value="<%=bean.getMailAddress()%>">
        </td>
      </tr>
      <%}%>
    </table>
    </p>
    <p>
      <input type="submit" value="更新">
  </form>
  </p>
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>
</body>
</html>