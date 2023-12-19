<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.KoutsuuBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<KoutsuuBean> koutsuuBeanList = (List<KoutsuuBean>) request.getAttribute(Path.KOUTSUU_SHOUNIN_SCOPE);
%>

<!DOCTYPE html>
<!-- 交通費精算承認画面  -->
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>交通費精算承認</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form action="<%=request.getContextPath()%>/KoutsuuShouninAction" method="post">
    <input type="hidden" name="type" value="3">
    <p>
      <input type="submit" value="振込済を表示しない">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/KoutsuuShouninAction" method="post">
    <input type="hidden" name="type" value="1">
    <p>
      <input type="submit" value="全て表示">
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/KoutsuuShouninAction" method="post">
    <input type="hidden" name="type" value="2">
    <p>
      <input type="submit" value="特定のステータスのみ表示">
      <br>
      <input type="radio" name="status" value=0 checked>
      申請中<br>
      <input type="radio" name="status" value=1>
      差戻中<br>
      <input type="radio" name="status" value=2>
      承認済<br>
      <input type="radio" name="status" value=3>
      振込完了
    </p>
  </form>

  <form action="<%=request.getContextPath()%>/KoutsuuShouninAction" method="post">
    <input type="hidden" name="type" value="0">
    <p>
      <input type="submit" value="特定のIDのみ表示">
      <br> ID:
      <input type="text" name="selId" maxlength="1000">
    </p>
  </form>

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
      <th>操作</th>
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
        <form action="<%=request.getContextPath()%>/ResultKoutsuuShouninAction" method="post">
          <input type="hidden" name="befStatus" value="<%=bean.getSeisannStatus()%>">
          <input type="hidden" name="status" value="1">
          <input type="hidden" name="selNo" value="<%=bean.getNo()%>">
          <input type="hidden" name="selId" value="<%=bean.getId()%>">
          <input type="submit" value="差戻">
        </form>
        <form action="<%=request.getContextPath()%>/ResultKoutsuuShouninAction" method="post">
          <input type="hidden" name="befStatus" value="<%=bean.getSeisannStatus()%>">
          <input type="hidden" name="status" value="2">
          <input type="hidden" name="selNo" value="<%=bean.getNo()%>">
          <input type="hidden" name="selId" value="<%=bean.getId()%>">
          <input type="submit" value="承認">
        </form>
        <form action="<%=request.getContextPath()%>/ResultKoutsuuShouninAction" method="post">
          <input type="hidden" name="befStatus" value="<%=bean.getSeisannStatus()%>">
          <input type="hidden" name="status" value="3">
          <input type="hidden" name="selNo" value="<%=bean.getNo()%>">
          <input type="hidden" name="selId" value="<%=bean.getId()%>">
          <input type="submit" value="振込">
        </form>
      </td>
    </tr>
    <%}%>
  </table>
  </p>

  <p>
    <a href="/TDSLWebProject/index.jsp">トップページに戻る</a>
  </p>
</body>
</html>