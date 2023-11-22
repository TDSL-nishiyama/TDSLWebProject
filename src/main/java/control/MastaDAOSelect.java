package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constents.Const.Common;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaEntity;

public class MastaDAOSelect extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  public int checkUserId(MastaEntity mastaEntity) {

    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(String.valueOf(mastaEntity.getUserid()));
    result = super.countSQL("checkUserId.sql", statement);

    return result;
  }

  public List<MastaEntity> getUserIchiran(String fileName) {

    List<MastaEntity> returnList = new ArrayList<>();
    int id = 0;
    String loginId = "";
    String loginName = "";
    boolean kanriFlg = false;

    Connection conn = null;

    try {
      // JDBCドライバ読み込み
      DBAccess.super.loadJDBCDriver();

      // DBへ接続
      conn = DBAccess.super.connectionDB(conn);

      //SQL文の作成
      sqlPath += fileName;
      String sql = null;
      sql = makeSQL(sqlPath);

      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECTを実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();

      //結果表に格納されたレコードの内容をMastaEntityに設定
      while (rs.next()) {
        id = rs.getInt("id");
        loginId = rs.getString("loginid");
        loginName = rs.getString("name");
        kanriFlg = rs.getBoolean("kanriFlg");

        MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId);
        returnList.add(mastaEntity);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBAccess.super.closeDB(conn);
    }

    sqlPath = Common.SQL_FILE_PATH;

    return returnList;
  }

  public List<MastaEntity> getHensyuUser(String fileName,Map<String, Object> statement) {

    List<MastaEntity> returnList = new ArrayList<>();
    int id = 0;
    String loginId = "";
    String loginName = "";
    boolean kanriFlg = false;

    Connection conn = null;

    try {
      // JDBCドライバ読み込み
      DBAccess.super.loadJDBCDriver();

      // DBへ接続
      conn = DBAccess.super.connectionDB(conn);

      //SQL文の作成
      sqlPath += fileName;
      String sql = null;
      sql = makeSQL(sqlPath);

      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      pStmt.setObject(1, statement.get("userIdUpd"));

      // SELECTを実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();

      //結果表に格納されたレコードの内容をMastaEntityに設定
      while (rs.next()) {
        id = rs.getInt("id");
        loginId = rs.getString("loginid");
        loginName = rs.getString("name");
        kanriFlg = rs.getBoolean("kanriFlg");

        MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId);
        returnList.add(mastaEntity);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBAccess.super.closeDB(conn);
    }
    
    sqlPath = Common.SQL_FILE_PATH;

    return returnList;
  }
}
