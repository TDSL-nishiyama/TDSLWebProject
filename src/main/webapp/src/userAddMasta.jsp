<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header.jsp"%>
<script type="text/javascript" src="./js/UserAddCheck.js" charset="UTF-8"></script>
<title>ユーザー登録画面</title>
</head>
<body>
  <%@ include file="../msg.jsp"%>
  <form name="userInsert" action="<%=request.getContextPath()%>/ResultUserAddAction" method="post" onsubmit="return checkUserAdd();">
    <p>
    <span class="req">※ 必須項目</span><br>
    <table>
      <tr><td><span class="req">※</span>ユーザー名：<input type="text" name="userName"></td></tr>
      <tr><td><span class="req">※</span>管理者権限：</td></tr>
      <tr><td><input type="radio" name="kanriFlg" value="1"> あり</td></tr>
      <tr><td><input type="radio" name="kanriFlg" value="0" checked> なし</td></tr>
    </table>
    <table>
      <tr><td><span class="req">※</span>姓：</td><td><input type="text" name="sei"></td><td><span class="req">※</span>名：</td><td><input type="text" name="mei"></td></tr>
      <tr><td><span class="req">※</span>姓(ﾖﾐ)：</td><td><input type="text" name="seiyomi"></td><td><span class="req">※</span>名(ﾖﾐ)：</td><td><input type="text" name="meiyomi"></td></tr>
      <tr><td>入社日付：</td><td><input type="text" name="nyuusyaYMD"></td></tr>
      <tr><td><span class="req">※</span>性別：</tr></td>
      <tr><td><input type="radio" name="seibetsu" value="0">男</td></tr>
      <tr><td><input type="radio" name="seibetsu" value="1">女</td></tr>
      <tr><td><input type="radio" name="seibetsu" value="2">その他</td></tr> 
      <tr><td><span class="req">※</span>生年月日：</td><td><input type="text" name="seinenngappi"></td></tr>
      <tr><td>出身地：</td><td><input type="text" name="syusshin"></td></tr>
      <tr><td><span class="req">※</span>住所：</td><td><input type="text" name="juusyo"></td></tr>
     </table>
      <p><input type="submit" value="ユーザー登録"></p>
    </p>
  </form>
  
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>

</body>
</html>