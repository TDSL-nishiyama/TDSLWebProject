<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.MastaBean,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaBean> mastaBeanlist = (List<MastaBean>) request.getAttribute(Path.USER_UPD_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<title>ユーザー更新実行画面</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form name="userKoushinJikkou" action="<%=request.getContextPath()%>/ResultUserUpdAction" method="post">
    <p>
    <span class="req">※ 必須項目</span>
    <table class="tableM">
      <tr>
        <th>ID</th>
        <th><span class="req">※</span>表示名</th>
        <th><span class="req">※</span>管理者権限</th>
      </tr>
      <%
      for (MastaBean mastaBean : mastaBeanlist) {
      %>
      <td><%=mastaBean.getUserid()%></td>
      <td>
        <input type=text " name="userName" value="<%=mastaBean.getUserName()%>"
      </td>
      <td>
        <%
        //mastaBeanから選択ユーザーの管理者権限を取得してcheckedを制御
        if (mastaBean.getKanriFlg() == false) {
        %>
        管理者権限: あり：
        <input type="radio" name="kanriFlg" value=1>
        なし：
        <input type="radio" name="kanriFlg" value=0 checked>
        <%
        } else {
        %>
        管理者権限: あり：
        <input type="radio" name="kanriFlg" value=1 checked>
        なし：
        <input type="radio" name="kanriFlg" value=0>
        <%
        }
        %>
      </td>
      <input type="hidden" name="hdnUserId" value=<%=mastaBean.getUserid()%>>
    </table>
    </p>
    <table class="tableM">
      <tr>
        <th><span class="req">※</span>姓</th>
        <th><span class="req">※</span>姓(ﾖﾐ)</th>
        <th><span class="req">※</span>名</th>
        <th><span class="req">※</span>名(ﾖﾐ)</th>
        <th><span class="req">※</span>性別</th>
        <th><span class="req">※</span>生年月日</th>
        <th>入社年月日</th>
        <th>出身地</th>
        <th><span class="req">※</span>住所</th>
      </tr>
      <tr>
        <td>
          <input type="text" name="sei" value=<%=mastaBean.getSei()%>>
        </td>
        <td>
          <input type="text" name="sei_yomi" value=<%=mastaBean.getSei_yomi()%>>
        </td>
        <td>
          <input type="text" name="mei" value=<%=mastaBean.getMei()%>>
        </td>
        <td>
          <input type="text" name="mei_yomi" value=<%=mastaBean.getMei_yomi()%>>
        </td>
        <td>
          <%
          if (mastaBean.getSeibetsu().equals("0")) {
          %>
          <input type="radio" name="seibetsu" value="0" checked>
          男
          <input type="radio" name="seibetsu" value="1">
          女
          <input type="radio" name="seibetsu" value="2">
          その他
          <%
          } else if (mastaBean.getSeibetsu().equals("1")) {
          %>
          <input type="radio" name="seibetsu" value="0">
          男
          <input type="radio" name="seibetsu" value="1" checked>
          女
          <input type="radio" name="seibetsu" value="2">
          その他
          <%
          } else {
          %>
          <input type="radio" name="seibetsu" value="0">
          男
          <input type="radio" name="seibetsu" value="1">
          女
          <input type="radio" name="seibetsu" value="2" checked>
          その他
          <%}%>
        </td>
        <td>
        <%
        //エラー時に生年月日がNULLで入力されてしまうためブランクに変換
        //NULLの場合はブランクに変換
        String getSeinenngappiView = mastaBean.getSeinenngappi();
        if (mastaBean.getSeinenngappi() == null) {
          getSeinenngappiView = "";
        }else{
          getSeinenngappiView = mastaBean.getSeinenngappi();
        }
        %>
          <input type="text" name="seinenngappi" value=<%=getSeinenngappiView%>>
        </td>
        <%
        //入社年月日は入力必須項目ではない日付項目のためNULLが出力されるケースがある
        //NULLの場合はブランクに変換
        String getNyuusyaYMDViwe = mastaBean.getNyuusyaYMD();
        if (mastaBean.getNyuusyaYMD() == null) {
          getNyuusyaYMDViwe = "";
        }else{
          getNyuusyaYMDViwe = mastaBean.getNyuusyaYMD();
        }
        %>
        <td>
          <input type="text" name="nyuusyaYMD" value=<%=getNyuusyaYMDViwe%>>
        </td>
        <td>
          <input type="text" name="syusshin" value=<%=mastaBean.getSyusshin()%>>
        </td>
        <td>
          <input type="text" name="juusyo" value=<%=mastaBean.getJuusyo()%>>
        </td>
      </tr>
    </table>
    <%
    }
    %>
    <p>
      <input type="submit" name="userUpd" value="更新">
    </p>
  </form>
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>

</body>
</html>