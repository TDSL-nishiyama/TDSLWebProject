<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

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
<title>仮登録ユーザー一覧</title>
</head>
<body>
  <!-- 11人目以降は2ページ目  -->
  <!-- 最大表示数は100人 -->
  <table border="1">
    <tr>
      <th>ID</th>
      <th>ログインID</th>
      <th>名前</th>
      <th>管理フラグ</th>
    </tr>
    <%
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