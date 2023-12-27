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
  <p>
  <table class="skillsheet">
    <tr>
      <th colspan="2">基本情報</th>
    </tr>
    <tr>
      <th>名前</th>
      <th>経験年数</th>
    </tr>
    <tr>
      <td><%=bean.getUserName()%></td>
      <td><%=bean.getNyuusyaYMDView()%></td>
    </tr>
  </table>
  <p>
  <table class="skillsheet">
    <tr>
      <th colspan="2">取得資格</th>
    </tr>
    <tr>
      <th>取得年月</th>
      <th>資格名称</th>
    </tr>
    <tr>
      <td><%=bean.getSikaku1YMD()%></td>
      <td><%=bean.getSikaku1()%></td>
    </tr>
    <tr>
      <td><%=bean.getSikaku2YMD()%></td>
      <td><%=bean.getSikaku2()%></td>
    </tr>
    <tr>
      <td><%=bean.getSikaku3YMD()%></td>
      <td><%=bean.getSikaku3()%></td>
    </tr>
  </table>

  <p>
  <table class="skillsheet">
    <tr>
      <th style="width: 50px">No</th>
      <th colspan="4">職務経歴</th>
    </tr>
    <tr>
      <th style="width: 50px" rowspan="6">1</th>
    </tr>
    <tr>
      <th colspan="3" width="">勤務年月等</th>
    </tr>
    <tr>
      <th>入場</th>
      <th>退場</th>
      <th>在籍期間</th>
    </tr>
    <tr>
      <td style="width: 200px"><%=bean.getC1SYMD()%></td>
      <td style="width: 200px"><%=bean.getC1EYMD()%></td>
      <td style="width: 200px"><%=bean.getC1kikanView()%></td>
    </tr>
    <tr>
      <th>職務内容</th>
      <th>担当職務</th>
      <th>使用言語等</th>
    </tr>
    <tr>
      <td width="600" style="overflow: normal"><%=bean.getCarrier1()%></td>
      <td style="overflow: normal"><%=bean.getC1pos()%></td>
      <td style="overflow: normal">言語：VB6・VB.net・VC++・C#.net・COBOL・Visual Cobol<br>DB：SQLServer・DB2</td>
    </tr>
        <tr>
      <th style="width: 50px" rowspan="6">2</th>
    </tr>
    <tr>
      <th colspan="3" width="">勤務年月等</th>
    </tr>
    <tr>
      <th>入場</th>
      <th>退場</th>
      <th>在籍期間</th>
    </tr>
    <tr>
      <td style="width: 200px"><%=bean.getC1SYMD()%></td>
      <td style="width: 200px"><%=bean.getC1EYMD()%></td>
      <td style="width: 200px"><%=bean.getC1kikanView()%></td>
    </tr>
    <tr>
      <th>職務内容</th>
      <th>担当職務</th>
      <th>使用言語等</th>
    </tr>
    <tr>
      <td width="600" style="overflow: normal"><%=bean.getCarrier1()%></td>
      <td style="overflow: normal"><%=bean.getC1pos()%></td>
      <td style="overflow: normal">言語：VB6・VB.net・VC++・C#.net<br>DB：MySQL</td>
    </tr>
        <tr>
      <th style="width: 50px" rowspan="6">3</th>
    </tr>
    <tr>
      <th colspan="3" width="">勤務年月等</th>
    </tr>
    <tr>
      <th>入場</th>
      <th>退場</th>
      <th>在籍期間</th>
    </tr>
    <tr>
      <td style="width: 200px"><%=bean.getC1SYMD()%></td>
      <td style="width: 200px"><%=bean.getC1EYMD()%></td>
      <td style="width: 200px"><%=bean.getC1kikanView()%></td>
    </tr>
    <tr>
      <th>職務内容</th>
      <th>担当職務</th>
      <th>使用言語等</th>
    </tr>
    <tr>
      <td width="600" style="overflow: normal"><%=bean.getCarrier1()%></td>
      <td style="overflow: normal"><%=bean.getC1pos()%></td>
      <td style="overflow: normal">言語：VB6・VB.net・VC++・C#.net<br>DB：MySQL</td>
    </tr>
  </table>
  <%
  }
  %>
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