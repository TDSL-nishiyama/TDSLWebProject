package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.Common;
import constents.Table.User;
import constents.KoutsuuConst.Koutsuu;
import constents.KoutsuuConst.KtimeStamp;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.KoutsuuBean;
import model.KoutsuuEntity;

public class KoutsuuDAO extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@ 交通費精算要求画面で請求ボタンを押下した際の処理}
   * @param bean
   * @throws SQLException
   */
  public void sendKoutsuu(KoutsuuBean bean) throws SQLException {
    List<Object> statement1 = new ArrayList<>();
    //ステートメントの設定

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);
    
    try {
      //トランザクション開始
      super.startTransaction(conn);
      //koutsuuテーブルの更新
      super.executeDMLMlt(conn, "", statement1);
      //ktimestampテーブルの更新
      super.executeDMLMlt(conn, "", statement1);
    } catch (SQLException e) {
      //ロールバック
      super.endTransactionFalse(conn);
      throw e;
    }
    //コミット
    super.endTransactionTrue(conn);
  }

  /**
   * {@ 交通費精算確認画面を開いた際の処理（一般ユーザー向け請求している/していた交通費の確認）}
   * @param selId 選択するid
   * @throws SQLException
   */
  public List<KoutsuuEntity> selectKoutsuuKakunin(int selId) throws SQLException {
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<Object>();
    List<KoutsuuEntity> result = new ArrayList<KoutsuuEntity>();

    //取得カラム名の追加
    column.add(Koutsuu.COL_UNINO);
    column.add(Koutsuu.COL_USERID);
    column.add(User.COL_USERNAME);
    column.add(Koutsuu.COL_SMAIL);
    column.add(Koutsuu.COL_KUKAN_S);
    column.add(Koutsuu.COL_KUKAN_E);
    column.add(Koutsuu.COL_KINGAKU);
    column.add(Koutsuu.COL_BIKOU);
    column.add(KtimeStamp.COL_STATUS);
    //ステートメントの追加
    statement.add(selId);

    //super.selectSQL("\\koutsuu\\getKoutsuuKakunin.sql", column, statement);

    sqlPath += "\\koutsuu\\getKoutsuuKakunin.sql";

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    try {
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      //ステートメントの設定
      if (statement != null) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }
      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      //実行結果の格納
      int no = 0;
      int id = 0;
      String userName = null;
      String sendMailAdress = null;
      String kukanStart = null;
      String kukanEnd = null;
      String kingaku = null;
      String bikou = null;
      String status = null;

      while (rs.next()) {
        no = rs.getInt(Koutsuu.COL_UNINO);
        id = rs.getInt(Koutsuu.COL_USERID);
        userName = rs.getString(User.COL_USERNAME);
        sendMailAdress = rs.getString(Koutsuu.COL_SMAIL);
        kukanStart = rs.getString(Koutsuu.COL_KUKAN_S);
        kukanEnd = rs.getString(Koutsuu.COL_KUKAN_E);
        kingaku = rs.getString(Koutsuu.COL_KINGAKU);
        bikou = rs.getString(Koutsuu.COL_BIKOU);
        status = rs.getString(KtimeStamp.COL_STATUS);
        KoutsuuEntity entity = new KoutsuuEntity(
            no, id, userName, sendMailAdress, kukanStart, kukanEnd, kingaku, bikou, status);
        result.add(entity);
      }

    } catch (SQLException e) {
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }

    return result;

  }

  public List<Object> selectSQL(String fileName, List<String> column, List<Object> statement) {
    List<Object> result = new ArrayList<Object>();

    return result;

  }

}
