<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.SyainJouhouBean,java.util.List,java.text.SimpleDateFormat"%>
<%@ page import="constents.Const.Path"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<SyainJouhouBean> syainJouhouBeanList = (List<SyainJouhouBean>) request.getAttribute(Path.SYAIN_HENSYU_SCOPE);
//日付変換クラスの用意
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>社員一覧</title>
</head>
<form name="toSJHen-SJInfo" action="<%=request.getContextPath()%>/ResultSyainJouhouHensyuuAction" method="post">
  <body>
    <%@ include file="../msg.jsp"%>
    <div class="req">※ 必須項目</div>
    <p>
    <table class="tableM">
      <tr>
        <th>社員ID</th>
        <th>
          <span class="req">※</span>姓
        </th>
        <th>
          <span class="req">※</span>姓(ﾖﾐ)
        </th>
        <th>
          <span class="req">※</span>名
        </th>
        <th>
          <span class="req">※</span>名(ﾖﾐ)
        </th>
        <th>
          <span class="req">※</span>性別
        </th>
        <th>
          <span class="req">※</span>生年月日
        </th>
        <th>入社年月日</th>
        <th>出身地</th>
        <th>
          <span class="req">※</span>住所
        </th>
      </tr>

      <%
      for (SyainJouhouBean syainJouhouBean : syainJouhouBeanList) {
      %>
      <tr>
        <td>
          <input type="hidden" name="updUserId" value=<%=syainJouhouBean.getId()%>><%=syainJouhouBean.getId()%></td>
        <td>
          <input type="text" name="sei" value=<%=syainJouhouBean.getSei()%>>
        </td>
        <td>
          <input type="text" name="sei_yomi" value=<%=syainJouhouBean.getSei_yomi()%>>
        </td>
        <td>
          <input type="text" name="mei" value=<%=syainJouhouBean.getMei()%>>
        </td>
        <td>
          <input type="text" name="mei_yomi" value=<%=syainJouhouBean.getMei_yomi()%>>
        </td>
        <td>
          <%
          if (syainJouhouBean.getSeibetsu().equals("男")) {
          %>
          <input type="radio" name="seibetsu" value="0" checked>
          男
          <input type="radio" name="seibetsu" value="1">
          女
          <input type="radio" name="seibetsu" value="2">
          その他
          <%
          } else if (syainJouhouBean.getSeibetsu().equals("女")) {
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
          <%
          }
          %>
        </td>
        <td>
        <%
        //エラー時に生年月日がNULLで入力されてしまうためブランクに変換
        //NULLの場合はブランクに変換
        String getSeinenngappiView = String.valueOf(syainJouhouBean.getSeinenngappi());
        if (syainJouhouBean.getSeinenngappi() == null) {
          getSeinenngappiView = "";
        }else{
          getSeinenngappiView = sdf.format(syainJouhouBean.getSeinenngappi());
        }
        %>
          <input type="text" name="seinenngappi" value=<%=getSeinenngappiView%>>
        </td>
        <%
        //入社年月日は入力必須ではない日付項目のためNULLが出力されるケースがある
        //NULLの場合はブランクに変換
        String getNyuusyaYMDViwe = String.valueOf(syainJouhouBean.getNyuusyaYMD());
        if (syainJouhouBean.getNyuusyaYMD() == null) {
          getNyuusyaYMDViwe = "";
        }
        %>
        <td>
          <input type="text" name="nyuusyaYMD" value=<%=getNyuusyaYMDViwe%>>
        </td>
        <td>
          <input type="text" name="syusshin" value=<%=syainJouhouBean.getSyusshin()%>>
        </td>
        <td>
          <input type="text" name="juusyo" value=<%=syainJouhouBean.getJuusyo()%>>
        </td>
      </tr>
      <%
      }
      %>
    </table>
    </p>

    <p>
      <input type="submit" value="更新実行">
</form>

<form name="toSJHen-SJInfo" action="<%=request.getContextPath()%>/SyainJouhouAction" method="post">
  <input type="submit" name="toSJHen-SJInfo" value="戻る">
</form>
</p>
</body>
</html>