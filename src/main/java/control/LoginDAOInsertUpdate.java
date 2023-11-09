package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAOInsertUpdate extends DBConnection {

  public void InsertLoginId(String pUserId, String pLoginId, String pLoginPassword) {

    Connection conn = null;

    try {

      // JDBCドライバを読み込み
      super.loadJDBCDriver();

      // DBへ接続
      conn = super.connectionDB(conn);

      // SQL文を準備
      StringBuilder sql = new StringBuilder();

      sql.append("INSERT INTO login(id,loginid,password) VALUES(?,?,?);");

      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      pStmt.setString(1, pUserId);
      pStmt.setString(2, pLoginId);
      pStmt.setString(3, pLoginPassword);

      //TODO Insertを実行　
      pStmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // データベース切断
      super.closeDB(conn);
    }
  }

  public void updateUserPassword(String pUserId, String pLoginId, String pLoginPassword) {

    Connection conn = null;

    try {

      // JDBCドライバを読み込み
      super.loadJDBCDriver();

      // DBへ接続
      conn = super.connectionDB(conn);

      // SQL文を準備
      StringBuilder sql = new StringBuilder();

      sql.append("UPDATE login SET loginid = ?,password = ? WHERE loginid = ?;");

      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      pStmt.setString(1, pLoginId);
      pStmt.setString(2, pLoginPassword);
      pStmt.setString(3, pUserId);

      // Updateを実行
      pStmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // データベース切断
      super.closeDB(conn);
    }
  }

  public void updatePassword(String pUserId, String pLoginPassword) {

    Connection conn = null;

    try {

      // JDBCドライバを読み込み
      super.loadJDBCDriver();

      // DBへ接続
      conn = super.connectionDB(conn);

      // SQL文を準備
      StringBuilder sql = new StringBuilder();

      sql.append("UPDATE login SET password = ? WHERE loginid = ?;");

      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      pStmt.setString(1, pLoginPassword);
      pStmt.setString(2, pUserId);

      // Updateを実行
      pStmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // データベース切断
      super.closeDB(conn);
    }
  }
}
