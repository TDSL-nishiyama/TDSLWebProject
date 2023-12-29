<%@page import="control.common.CastCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.SkillBean,control.common.CastCommon,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SkillBean> skillBeanList = (List<SkillBean>) request.getAttribute(Path.SKILL_SCOPE);
//共通クラスのインスタンス化
CastCommon castC = new CastCommon();
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form action="<%=request.getContextPath()%>/ResultSkillHensyuuAction" method="post">
    <%
    for (SkillBean bean : skillBeanList) {
    %>
    <p>
    <input type="hidden" name="selId" value=<%=skillBeanList.get(0).getUserId()%>>
    <input type="submit" value="更新実行">
    </p>
    
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
        <td>
          <input type="text"size="50" name="sikaku1YMD" value="<%=bean.getSikaku1YMD()%>">
        </td>
        <td>
          <input type="text"size="100" name="sikaku1" value="<%=bean.getSikaku1()%>">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" size="50" name="sikaku2YMD" value="<%=bean.getSikaku2YMD()%>">
        </td>
        <td>
          <input type="text" size="100" name="sikaku2" value="<%=bean.getSikaku2()%>">
        </td>
      </tr>
      <tr>
        <td><input type="text" size="50" name="sikaku3YMD" value="<%=bean.getSikaku3YMD()%>"></td>
        <td><input type="text" size="100" name="sikaku3" value="<%=bean.getSikaku3()%>"></td>
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
        <td style="width: 200px"><input type="text" name="c1sYMD" value="<%=bean.getC1SYMD()%>"></td>
        <td style="width: 200px"><input type="text" name="c1eYMD" value="<%=bean.getC1EYMD()%>"></td>
        <td style="width: 200px"><%=bean.getC1kikanView()%></td>
      </tr>
      <tr>
        <th>職務内容</th>
        <th>担当職務</th>
        <th>使用言語等</th>
      </tr>
      <tr>
        <td width="600" style="overflow: normal"><textarea name="carrier1" rows="20" cols="90"><%=castC.chgBRtag(bean.getCarrier1())%></textarea></td>
        <td style="overflow: normal"><textarea name="c1pos" rows="20" cols="30" name="c1pos"><%=castC.chgBRtag(bean.getC1pos())%></textarea></td>
        <td style="overflow: normal"><textarea name="c1tech" rows="20" cols="30" name="c1tech"><%=castC.chgBRtag(bean.getC1tech())%></textarea></td>
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
        <td style="width: 200px"><input type="text" name="c2sYMD" value="<%=bean.getC2SYMD()%>"></td>
        <td style="width: 200px"><input type="text" name="c2eYMD" value="<%=bean.getC2EYMD()%>"></td>
        <td style="width: 200px"><%=bean.getC2kikanView()%></td>
      </tr>
      <tr>
        <th>職務内容</th>
        <th>担当職務</th>
        <th>使用言語等</th>
      </tr>
      <tr>
        <td width="600" style="overflow: normal"><textarea name="carrier2" rows="20" cols="90"><%=castC.chgBRtag(bean.getCarrier2())%></textarea></td>
        <td style="overflow: normal"><textarea name="c2pos" rows="20" cols="30" name="c2pos"><%=bean.getC2pos()%></textarea></td>
        <td style="overflow: normal"><textarea name="c2tech" rows="20" cols="30" name="c2tech"><%=bean.getC2tech()%></textarea></td>
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
        <td style="width: 200px"><input type="text" name="c3sYMD" value="<%=bean.getC3SYMD()%>"></td>
        <td style="width: 200px"><input type="text" name="c3eYMD" value="<%=bean.getC3EYMD()%>"></td>
        <td style="width: 200px"><%=bean.getC3kikanView()%></td>
      </tr>
      <tr>
        <th>職務内容</th>
        <th>担当職務</th>
        <th>使用言語等</th>
      </tr>
      <tr>
        <td width="600" style="overflow: normal"><textarea name="carrier3" rows="20" cols="90"><%=bean.getCarrier3()%></textarea></td>
        <td style="overflow: normal"><textarea name="c3pos" rows="20" cols="30" name="c3pos"><%=bean.getC3pos()%></textarea></td>
        <td style="overflow: normal"><textarea name="c3tech" rows="20" cols="30" name="c3tech"><%=bean.getC3tech()%></textarea></td>
      </tr>
    </table>
    <%
    }
    %>
  </form>
  <p>
  <form action="<%=request.getContextPath()%>/SkillAction" method="post">
    <input type="hidden" name="selId" value=<%=skillBeanList.get(0).getUserId()%>>
    <input type="submit" value="スキルシート閲覧画面に戻る">
  </form>
  </p>
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