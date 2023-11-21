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
      <span class="req">※</span>ユーザー名：<input type="text" name="userName"><br>
      <span class="req">※</span>管理者権限：<br>
      <input type="radio" name="kanriFlg" value="1"> あり<br>
      <input type="radio" name="kanriFlg" value="0" checked> なし<br><br>
      
      <span class="req">※</span>姓：<input type="text" name="sei"> <span class="req">※</span>名：<input type="text" name="mei"><br>
      <span class="req">※</span>姓(ﾖﾐ)：<input type="text" name="seiyomi"> <span class="req">※</span>名(ﾖﾐ)：<input type="text" name="meiyomi"><br>
      入社日付：<input type="text" name="nyuusyaYMD"><br>
      <span class="req">※</span>性別：<br>
      <input type="radio" name="seibetsu" value="0">男<br>
      <input type="radio" name="seibetsu" value="1">女<br>
      <input type="radio" name="seibetsu" value="2">その他<br>      
      <span class="req">※</span>生年月日：<input type="text" name="seinenngappi"><br>
      出身地：<input type="text" name="syusshin"><br>
      <span class="req">※</span>住所：<input type="text" name="juusyo"><br><br>
      
      <input type="submit" value="ユーザー登録"><br>
    </p>
  </form>
  
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>

</body>
</html>