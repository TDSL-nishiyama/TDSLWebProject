package control.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.Common;

public class DAOCommon implements DBAccess {

  //SQLファイルの格納フォルダを指定
  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@index} SELECT COUNT 実行メソッド（MAX等他の集計関数での利用も可能）
   * @param fileName 実行したいSQLファイルの名前
   * @param statement PreparedStatmentの内容　List<Object>　ない場合はNULLを指定してください
   * @return select count の結果(int)
   */
  protected int countSQL(String fileName, List<Object> statement) {

    int result = 0;
    sqlPath += fileName;

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    //クエリの発行・格納
    try {
      //ステートメントの設定
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      if (statement != null) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }
      //発行
      ResultSet rs = pStmt.executeQuery();

      //格納
      while (rs.next()) {
        result = Integer.parseInt(rs.getString(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      //SQLファイルパスの初期化
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }
    return result;
  }

  /**
   * {@index} SELECT実行メソッド　（1レコード）
   * @param fileName 実行したいSQLファイルの名前
   * @param column 取得したいカラム名
   * @return selectの結果(List)
   */
  protected List<Object> selectSQL(String fileName, List<String> column) {

    List<Object> result = new ArrayList<Object>();
    sqlPath += fileName;

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    //クエリの発行・格納
    try {
      //発行
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      int listCnt = column.size();

      //格納
      while (rs.next()) {
        for (int i = 0; i < listCnt; i++) {
          result.add(rs.getObject(column.get(i)));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      //SQLファイルパスの初期化
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }

    return result;
  }

  /**
   * {@index} SELECT実行メソッド ?あり　（1レコード）
   * @param fileName 実行したいSQLファイルの名前
   * @param column 取得したいカラム名　List<String>
   * @param statement PreparedStatmentの内容　List<Object>
   * @return selectの結果(List)
   * @throws SQLException 
   */
  protected List<Object> selectSQL(String fileName, List<String> column, List<Object> statement) {

    List<Object> result = new ArrayList<Object>();
    sqlPath += fileName;

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    //クエリの発行・格納
    try {
      //発行
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());

      int colCnt = column.size();
      int steCnt = statement.size();

      //PreparedStatementの設定
      for (int i = 0; i < steCnt; i++) {
        pStmt.setObject(i + 1, statement.get(i));
      }

      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      //格納
      while (rs.next()) {
        for (int i = 0; i < colCnt; i++) {
          result.add(rs.getObject(column.get(i)));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      //SQLファイルパスの初期化
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }
    return result;
  }

  /**
   * {@index} INSERT・UPDATE・DLETE実行メソッド
   * @param fileName 実行したいSQLファイルの名前
   * @param statement PreparedStatmentの内容　List<Object>　ない場合はNULLを指定してください
   * @return selectの結果(List)
   * @throws SQLException 
   */
  protected void executeDML(String fileName, List<Object> statement) throws SQLException {

    sqlPath += fileName;

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);
    //オートコミットをオフにする（COMMIT/ROLLBACK処理を行わないと実行結果が反映されない）
    conn.setAutoCommit(false);
    PreparedStatement pStmt = conn.prepareStatement("START TRANSACTION");
    //クエリの実行
    pStmt.executeUpdate();
    
    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    try {
      //ステートメントの設定
      pStmt = conn.prepareStatement(sql.toString());
      if (!(statement.equals(null))) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }
      //クエリの実行
      pStmt.execute();
    } catch(SQLException e){
      conn.rollback();
      throw e;
    } finally {
      //SQLファイルパスの初期化
      sqlPath = Common.SQL_FILE_PATH;
    }
    conn.commit();
  }
  
  /**
   * {@index} INSERT・UPDATE・DLETE実行メソッド（複数テーブル同時更新向け）
   * @param conn Connection（一つ上の階層で宣言）
   * @param fileName 実行したいSQLファイルの名前
   * @param statement PreparedStatmentの内容　List<Object>　ない場合はNULLを指定してください
   * @return selectの結果(List)
   * @throws SQLException 
   */
  protected void executeDMLMlt(Connection conn,String fileName, List<Object> statement) throws SQLException {

    sqlPath += fileName;
    
    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    try {
      //ステートメントの設定
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      if (!(statement.equals(null))) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }
      //クエリの実行
      pStmt.executeUpdate();
    } catch(SQLException e){
      throw e;
    } finally {
      //SQLファイルパスの初期化
      sqlPath = Common.SQL_FILE_PATH;
    }
  }
  
  /**
   *{@index} トランザクション開始処理
   * @param  conn　Connection
   * @throws SQLException 
   **/
  public Connection startTransaction(Connection conn) throws SQLException {

    //オートコミットをオフにする（COMMIT/ROLLBACK処理を行わないと実行結果が反映されない）
    conn.setAutoCommit(false);

    //SQL文の作成
    String sql = null;
    sql = "START TRANSACTION;";

    //クエリの発行・格納
    //発行
    PreparedStatement pStmt = conn.prepareStatement(sql.toString());
    //クエリの実行
    pStmt.execute();
    
    return conn;
  }
  
  /**
   *{@index} トランザクション終了処理（正常系）
   * @param conn　Connection
   * @throws SQLException 
   **/
  public void endTransactionTrue(Connection conn) {

    //SQL文の作成
    String sql = null;
    sql = "COMMIT;";

    //クエリの発行・格納
    //発行
    PreparedStatement pStmt;
    try {
      pStmt = conn.prepareStatement(sql.toString());
      //クエリの実行
      pStmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      //DB切断
      DBAccess.super.closeDB(conn);
    }
  }

  /**
   *{@index} トランザクション終了処理（異常系）
   * @param conn　Connection
   * @throws SQLException 
   **/
  public void endTransactionFalse(Connection conn) {
    //SQL文の作成
    String sql = null;
    sql = "ROLLBACK;";

    //クエリの発行・格納
    //発行
    PreparedStatement pStmt;
    try {
      pStmt = conn.prepareStatement(sql.toString());
      //クエリの実行
      pStmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      //DB切断
      DBAccess.super.closeDB(conn);
    }
  }

  /**
   *{@index} SQL文作成　
   *@param String loadPath プロパティファイルが格納されているパス（フルパス） 
   *@return ファイルから読み込まれたSQL文（改行不可）
   **/
  protected String makeSQL(String sqlPath) {
    String result = null;

    LoadFile loadfile = new LoadFile();
    try {
      result = loadfile.getSQLFile(sqlPath);
    } catch (NumberFormatException | IOException e) {
      e.printStackTrace();
    }

    return result;
  }
}
