<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.SkillBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SkillBean> skillBeanList = (List<SkillBean>) request.getAttribute(Path.SKILL_SCOPE);
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
  for (SkillBean bean : skillBeanList) {
  %>
  <table border="1">
    <tr>
      <th>名前</th>
      <th>経験年数</th>
    </tr>
    <tr>
      <td><%=bean.getUserName()%></td>
      <td><%=bean.getNyuusyaYMDView()%></td>
    </tr>

    <tr>
      <th>取得年月</th>
      <th>資格１</th>
      <th>取得年月</th>
      <th>資格２</th>
      <th>取得年月</th>
      <th>資格３</th>
    </tr>
    <tr>
      <td><%=bean.getSikaku1YMD()%></td>
      <td><%=bean.getSikaku1()%></td>
      <td><%=bean.getSikaku2YMD()%></td>
      <td><%=bean.getSikaku2()%></td>
      <td><%=bean.getSikaku3YMD()%></td>
      <td><%=bean.getSikaku3()%></td>
    </tr>
    <tr>
      <th>職歴１開始</th>
      <td><%=bean.getC1SYMD()%></td>
      <th>職歴１終了</th>
      <td><%=bean.getC1kikanView()%></td>
      <th>職歴１期間</th>
      <td><%=bean.getC1EYMD()%></td>
    </tr>
    <tr>
      <th width="10px" height="100" style="overflow: normal">職歴１</th>
      <td><%=bean.getCarrier1()%></td>
      <th>担当職務</th>
      <td width="10px" height="100"><%=bean.getC1pos()%></td>
    </tr>
    <%
    }
    %>
  </table>
  <p>
  <form action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
    <input type="submit" value="社員情報編集画面に戻る">
  </form>
  </p>
  <p>
    <a href="/TDSLWebProject/index.jsp">トップページに戻る</a>
  </p>
</body>
</html>