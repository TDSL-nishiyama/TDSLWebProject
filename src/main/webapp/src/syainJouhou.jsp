<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouBean> syainJouhouBeanList = (List<SyainJouhouBean>) request.getAttribute(Path.SYAIN_JOUHOU_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <%
  if (kanriFlg == false) {
  %>
  <!-- 一般ユーザー -->
  <table class="tableM">
    <tr>
      <th>社員ID</th>
      <th>名前</th>
      <th>入社年月日</th>
      <th>年次</th>
      <th>出身地</th>
      <th>住所</th>
      <th>編集</th>
      <th>スキルシート閲覧</th>
      <th>スキルシート編集</th>
    </tr>
    <%
    for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
    %>
    <tr>
      <td><%=syainJouhouBean.getId()%></td>
      <td><%=syainJouhouBean.getName()%></td>
      <%
      //入社年月日は入力必須項目ではない日付項目のためNULLが出力されるケースがある
      //NULLの場合はブランクに変換
      String getNyuusyaYMDViwe = syainJouhouBean.getNyuusyaYMD();
      if (syainJouhouBean.getNyuusyaYMD() == null) {
        getNyuusyaYMDViwe = "";
      }
      %>
      <td><%=getNyuusyaYMDViwe%></td>
      <td><%=syainJouhouBean.getNenji()%></td>
      <td><%=syainJouhouBean.getSyusshin()%></td>
      <td><%=syainJouhouBean.getJuusyo()%></td>
      <td>
        <form action="<%=request.getContextPath()%>/SyainJouhouHensyuuAction" method="post">
          <input type="hidden" name="chgUserId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="編集">
        </form>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/SkillAction" method="post">
          <input type="hidden" name="selId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="スキルシート閲覧">
        </form>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/SkillHensyuuAction" method="post">
          <input type="hidden" name="selId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="スキルシート編集">
        </form>
      </td>
    </tr>
    <%
    }
    %>
  </table>
  <%
  } else {
  %>
  <!-- 管理者 -->
  <table class="tableM">
    <tr>
      <th>社員ID</th>
      <th>名前</th>
      <th>性別</th>
      <th>年齢</th>
      <th>入社年月日</th>
      <th>年次</th>
      <th>出身地</th>
      <th>住所</th>
      <th>編集</th>
      <th>スキルシート閲覧</th>
      <th>スキルシート編集</th>
    </tr>
    <%
    for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
    %>
    <tr>
      <td><%=syainJouhouBean.getId()%></td>
      <td><%=syainJouhouBean.getName()%></td>
      <td><%=syainJouhouBean.getSeibetsu()%></td>
      <td><%=syainJouhouBean.getAge()%></td>
      <%
      //入社年月日は入力必須項目ではない日付項目のためNULLが出力されるケースがある
      //NULLの場合はブランクに変換
      String getNyuusyaYMDViwe = syainJouhouBean.getNyuusyaYMD();
      if (syainJouhouBean.getNyuusyaYMD() == null) {
        getNyuusyaYMDViwe = "";
      }
      %>
      <td><%=getNyuusyaYMDViwe%></td>
      <td><%=syainJouhouBean.getNenji()%></td>
      <td><%=syainJouhouBean.getSyusshin()%></td>
      <td><%=syainJouhouBean.getJuusyo()%></td>
      <td>
        <form action="<%=request.getContextPath()%>/SyainJouhouHensyuuAction" method="post">
          <input type="hidden" name="chgUserId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="編集">
        </form>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/SkillAction" method="post">
          <input type="hidden" name="selId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="スキルシート閲覧">
        </form>
      </td>
      <td>
        <form action="<%=request.getContextPath()%>/SkillHensyuuAction" method="post">
          <input type="hidden" name="selId" value="<%=syainJouhouBean.getId()%>">
          <input type="submit" value="スキルシート編集">
        </form>
      </td>
    </tr>
    <%
    }
    %>
  </table>
  <%
  }
  %>
  <p>
    <a href="/TDSLWebProject/index.jsp">戻る</a>
  </p>
</body>
</html>