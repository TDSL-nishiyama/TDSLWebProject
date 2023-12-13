<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.KoutsuuBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<KoutsuuBean> koutsuuBeanList = (List<KoutsuuBean>) request.getAttribute(Path.KOUTSUU_KAKUNIN_SCOPE);
%>

<!DOCTYPE html>
<!-- 交通費精算確認画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>交通費精算確認</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <p>
  <table class="tableM">
    <tr>
      <th>請求No</th>
      <th>請求者</th>
      <th>ステータス</th>
      <th>申請日付</th>
      <th>利用日付</th>
      <th>区間開始</th>
      <th>区間終了</th>
      <th>金額</th>
      <th>備考</th>
      <th>差戻理由</th>
      <th>修正</th>
    </tr>
    <%
    for (KoutsuuBean bean : koutsuuBeanList) {
    %>
    <tr>
      <td>
        <%=bean.getNo()%>
      </td>
      <td>
        <%=bean.getUserName()%>
      </td>
      <td>
        <%=bean.getSeisannStatus()%>
      </td>
      <td>
        <%=bean.getYoukyuuView()%>
      </td>
      <td>
        <%=bean.getRiyouView()%>
      </td>
      <td>
        <%=bean.getKukan_start()%>
      </td>
      <td>
        <%=bean.getKukan_end()%>
      </td>
      <td>
        <%=bean.getKingaku()%>
      </td>
      <td>
        <%=bean.getBikou()%>
      </td>
      <td>
        <%="差戻理由"%>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/KoutsuuSashimodoshiAction" method="post">
          <input type="submit" value="修正">
        </form>
      </td>
    </tr>
    <%}%>
  </table>
  </p>

  <form action="<%=request.getContextPath()%>/KoutsuuYoukyuuAction" method="post">
    <p>
      <input type="submit" value="交通費精算要求画面に遷移">
    </p>
  </form>

  <p>
    <a href="/TDSLWebProject/index.jsp">トップページに戻る</a>
  </p>
</body>
</html>