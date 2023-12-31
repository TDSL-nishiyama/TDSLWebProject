<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="model.MastaEntity,java.util.List"%>
<%@ page import="constents.Const.Path"%>

<%
//リクエストスコープに保存されたユーザー情報を取得
List<MastaEntity> mastaEntitylist = (List<MastaEntity>) request.getAttribute(Path.USER_ICHIRAN_SCOPE);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
<title>ユーザー一覧</title>
</head>
<body>
  <form action=<%=request.getContextPath()%>/UserAddAction method="post">
    <p>
      <input type="submit" value="ユーザーの追加">
    </p>
  </form>

  <form name="toKaritouroku" action="<%=request.getContextPath()%>/KaritourokuAction" method="post">
    <p>
      <input type="submit" name="toKari" value="仮登録ユーザー一覧">
    </p>
  </form>
  <p>
  <table>
    <tr>
      <td>
        <form name="toResultUserIchiran" action=<%=request.getContextPath()%>/ResultUserIchiranAction method="post">
          <input type="submit" value="削除されたユーザーも含めて表示">
        </form>
      </td>
      <td>
        <form name="toUserIchiran" action="<%=request.getContextPath()%>/UserIchiranAction" method="post">
          <input type="submit" name="toMasta" value="削除されたユーザーを含まず表示">
        </form>
      </td>
    </tr>
  </table>
  </p>
  <!--TODO 仕様迷い中、ページディレクションのJSPのみでの実装がしんどい  -->
  <!-- 11人目以降は2ページ目  -->
  <!-- 最大表示数は100人 -->
  <table class="tableM">
    <tr>
      <th>ID</th>
      <th>ログインID</th>
      <th>名前</th>
      <th>管理フラグ</th>
      <th>更新</th>
      <th>削除</th>
    </tr>
    <%
    int idView = 0;
    String kanriFlgView = "";

    for (MastaEntity mastaEntity : mastaEntitylist) {
    %>
    <td><%=mastaEntity.getUserid()%></td>
    <td><%=mastaEntity.getLoginid()%></td>
    <td><%=mastaEntity.getUserName()%></td>
    <td>
      <%
      //管理フラグtrueの場合、〇　falseの場合、×　を表示
      if (mastaEntity.getKanriFlg() == true) {
        kanriFlgView = "〇";
      } else {
        kanriFlgView = "×";
      }
      %>
      <%=kanriFlgView%>
    </td>
    <td>
      <form action="<%=request.getContextPath()%>/ResultUserUpdAction" method="post">
        <input type="hidden" name="userIdUpd" value="<%=mastaEntity.getUserid()%>">
        <input type="submit" value="更新">
      </form>
    </td>
    <td>
      <form action="<%=request.getContextPath()%>/ResultUserDelAction" method="post">
        <input type="hidden" name="userIdDel" value="<%=mastaEntity.getUserid()%>">
        <input type="submit" value="削除">
      </form>
    </td>
    </tr>
    <%
    }
    %>
  </table>
  <p>
    <a href="/TDSLWebProject/masta.jsp">戻る</a>
  </p>
</body>
</html>