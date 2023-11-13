<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouBean> syainJouhouBeanList = (List<SyainJouhouBean>) request.getAttribute(Path.SYAIN_HENSYU_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>

<body>
  <%
  if (kanriFlg == false) {
  %>
  <!-- 一般ユーザー -->
  <table border="1">
    <tr>
      <th>社員ID</th>
      <th>名前</th>
      <th>入社年月日</th>
      <th>出身地</th>
    </tr>

    <%
    for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
    %>
    <tr>
      <td><input type="text" value="<%=syainJouhouBean.getId()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getName()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getNyuusyaYMD()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getSyusshin()%>"></td>
    </tr>
    <%
    }
    %>

  </table>
  <%
  } else {
  %>
  <!-- 管理者 -->
  <table border="1">
    <tr>
      <th>社員ID</th>
      <th>名前</th>
      <th>入社年月日</th>
      <th>出身地</th>
    </tr>

    <%
    for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
    %>
    <tr>
      <td><input type="text" value="<%=syainJouhouBean.getId()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getName()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getNyuusyaYMD()%>"></td>
      <td><input type="text" value="<%=syainJouhouBean.getSyusshin()%>"></td>
    </tr>
    <%
    }
    %>

  </table>
  <%
  }
  %>
  <p>
  
  <form name="toSJHen-SJInfo"
    action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
    <p>
      <input type="submit" name="toSJHen-SJInfo" value="戻る">
    </p>

  </form>


</body>
</html>