package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import constents.Const.Common;
import constents.Table.User;
import constents.KoutsuuConst.KCommon;
import constents.KoutsuuConst.Koutsuu;
import constents.KoutsuuConst.KtimeStamp;
import control.common.CastCommon;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.KoutsuuEntity;

public class KoutsuuDAO extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@index koutsuuテーブルのNoの最大値を取得}
   * @return
   */
  public int getMaxNo() {
    int result = super.countSQL("\\koutsuu\\maxNo.sql", null);
    return result;
  }

  /**
   * {@ 交通費精算要求画面で請求ボタンを押下した際の処理}
   * @param pStatement　ステートメントとして設定したい値List<Map<String,Object>>　←わかりづらい？もっといいのない？
   * @throws SQLException
   */
  public void InsertKoutsuuAndKtimestamp(List<Map<String, Object>> pStatement) throws SQLException {
    //ステートメントの設定
    List<Object> statement1 = new ArrayList<>();//koutsuuテーブル
    statement1.add(pStatement.get(0).get(Koutsuu.COL_UNINO));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_USERID));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_SMAIL));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_RIYOUHIDUKE));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_KUKAN_S));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_KUKAN_E));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_KINGAKU));
    statement1.add(pStatement.get(0).get(Koutsuu.COL_BIKOU));

    List<Object> statement2 = new ArrayList<>();//ktimestampテーブル
    statement2.add(pStatement.get(0).get(Koutsuu.COL_UNINO));
    statement2.add(pStatement.get(0).get(KtimeStamp.COL_YOUKYUU));
    statement2.add(pStatement.get(0).get(KtimeStamp.COL_STATUS));
    statement2.add(pStatement.get(0).get(KtimeStamp.COL_TIMESTAMP));

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    try {
      //トランザクション開始
      super.startTransaction(conn);
      //koutsuuテーブルの更新
      super.executeDMLMlt(conn, "koutsuu\\insertKoutsuu.sql", statement1);
      //ktimestampテーブルの更新
      super.executeDMLMlt(conn, "koutsuu\\insertKtimestamp.sql", statement2);
    } catch (SQLException e) {
      //ロールバック
      super.endTransactionFalse(conn);
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
    }
    //コミット
    super.endTransactionTrue(conn);
  }

  /**
   * {@ 交通費精算(確認/承認)画面を開いた際の処理（請求している/していた交通費の確認）}
   * @param selId 選択するid （全IDを表示したい場合0を指定）
   * @param selId 選択するno （指定のない場合0を指定）
   * @param selStatus 選択するステータス（0=申請中 1=差戻中 2=承認済 3=振込済）
   * @param selQuery 選択するクエリ 0=選択したIDのみ 1=すべてのID 2=ステータスが指定のもののみ 3=特定のステータスを除外 4=選択したNOのみ
   * @throws SQLException
   */
  public List<KoutsuuEntity> selectKoutsuuKakunin(int selId, int selNo, String selStatus, String selQuery)
      throws SQLException {
    List<Object> statement = new ArrayList<Object>();
    List<KoutsuuEntity> result = new ArrayList<KoutsuuEntity>();

    //共通クラスのインスタンス化
    CastCommon castCommon = new CastCommon();

    //SQL文の選択とステートメントの追加
    switch (selQuery) {
    case KCommon.QUERY_TYPE_0_SELID:
      sqlPath += "koutsuu\\getKoutsuuKakunin.sql";
      statement.add(selId);
      break;
    case KCommon.QUERY_TYPE_1_ALL:
      sqlPath += "koutsuu\\getKoutsuuKakuninAll.sql";
      break;
    case KCommon.QUERY_TYPE_2_SELSTA:
      sqlPath += "koutsuu\\getKoutsuuKakuninWhereStatus.sql";
      statement.add(selStatus);
      break;
    case KCommon.QUERY_TYPE_3_JOGAI:
      sqlPath += "koutsuu\\getKoutsuuKakuninJogai.sql";
      statement.add(selStatus);
      break;
    case KCommon.QUERY_TYPE_4_SELNO:
      sqlPath += "koutsuu\\getKoutsuuKakuninWhereNo.sql";
      statement.add(selNo);
      break;
    default:
      sqlPath += "koutsuu\\getKoutsuuKakuninAll.sql";
    }
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
      LocalDateTime riyouYMD = null;
      String sendMailAdress = null;
      String kukanStart = null;
      String kukanEnd = null;
      String kingaku = null;
      String bikou = null;
      String modoshiriyuu = null;
      String status = null;
      LocalDateTime shinseiYMD = null;
      LocalDateTime sashimodoshiYMD = null;
      LocalDateTime syouninYMD = null;

      //日付項目(MySQL:DateTime)はDateからLocalDateTimeに変換して格納
      //Date型でキャストしないとjava.sql.toInstant()が呼ばれてUnsupportedOperationExceptionが発生するため
      while (rs.next()) {
        no = rs.getInt(Koutsuu.COL_UNINO);
        id = rs.getInt(Koutsuu.COL_USERID);
        userName = rs.getString(User.COL_USERNAME);
        sendMailAdress = rs.getString(Koutsuu.COL_SMAIL);
        riyouYMD = castCommon.chgDtoLD(new Date(rs.getDate(Koutsuu.COL_RIYOUHIDUKE).getTime()));
        kukanStart = rs.getString(Koutsuu.COL_KUKAN_S);
        kukanEnd = rs.getString(Koutsuu.COL_KUKAN_E);
        kingaku = rs.getString(Koutsuu.COL_KINGAKU);
        bikou = rs.getString(Koutsuu.COL_BIKOU);
        modoshiriyuu = rs.getString(Koutsuu.COL_MODOSHIRIYUU);
        status = rs.getString(KtimeStamp.COL_STATUS);
        shinseiYMD = castCommon.chgDtoLD(new Date(rs.getDate(KtimeStamp.COL_YOUKYUU).getTime()));
        if (rs.getDate(KtimeStamp.COL_SASHIMODOSHI) != null) {
          sashimodoshiYMD = castCommon.chgDtoLD(new Date(rs.getDate(KtimeStamp.COL_SASHIMODOSHI).getTime()));
        }
        if (rs.getDate(KtimeStamp.COL_SYONIN) != null) {
          syouninYMD = castCommon.chgDtoLD(new Date(rs.getDate(KtimeStamp.COL_SYONIN).getTime()));
        }
        KoutsuuEntity entity = new KoutsuuEntity(
            no, id, userName, sendMailAdress, riyouYMD, kukanStart, kukanEnd, kingaku, bikou, modoshiriyuu,
            status, shinseiYMD, sashimodoshiYMD, syouninYMD);
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

  /**
   * {@index 交通費精算承認画面でのテーブル更新処理}
   * @param pStatement　Map<String,Object> 設定するステートメント
   * @throws SQLException
   */
  public void updateKoutsuuAndKtimestamp(Map<String, Object> pStatement) throws SQLException {
    //ステートメントの設定
    List<Object> statement1 = new ArrayList<>();//koutsuuテーブル
    List<Object> statement2 = new ArrayList<>();//ktimestampテーブル
    //set句
    String status = pStatement.get(KtimeStamp.COL_STATUS).toString();
    statement2.add(status);
    if (status.equals(KCommon.SYOUNIN)) {
      statement1.add(pStatement.get(Koutsuu.COL_MODOSHIRIYUU));
      statement2.add(pStatement.get(KtimeStamp.COL_SYONIN));
    }
    statement2.add(pStatement.get(KtimeStamp.COL_TIMESTAMP));
    //where句
    statement1.add(pStatement.get(KtimeStamp.COL_UNINO));
    statement2.add(pStatement.get(KtimeStamp.COL_UNINO));

    //テーブルの更新
    if (status.equals(KCommon.SYOUNIN)) {
      super.executeDML("koutsuu\\updateSyoninKoutsuu.sql", statement1);
      super.executeDML("koutsuu\\updateSyoninKtimestamp.sql", statement2);
    } else {
      super.executeDML("koutsuu\\updateFurikomiKtimestamp.sql", statement2);
    }
    sqlPath = Common.SQL_FILE_PATH;
  }

  /**
   * {@index 差戻ボタン押下時のテーブル更新処理}
   * @param pStatement
   * @throws SQLException
   */
  public void updateKoutsuuSashimodoshi(Map<String, Object> pStatement) throws SQLException {
    //ステートメントの設定
    List<Object> statement1 = new ArrayList<>();//koutsuuテーブル
    List<Object> statement2 = new ArrayList<>();//ktimestampテーブル

    //set句
    statement1.add(pStatement.get(Koutsuu.COL_MODOSHIRIYUU));
    statement2.add(pStatement.get(KtimeStamp.COL_STATUS));

    statement2.add(pStatement.get(KtimeStamp.COL_SASHIMODOSHI));
    statement2.add(pStatement.get(KtimeStamp.COL_TIMESTAMP));
    //where句
    statement1.add(pStatement.get(KtimeStamp.COL_UNINO));

    statement2.add(pStatement.get(KtimeStamp.COL_UNINO));

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //テーブルの更新
    try {
      super.startTransaction(conn);
      super.executeDMLMlt(conn, "koutsuu\\updateSashimodoshiKoutsuu.sql", statement1);
      super.executeDMLMlt(conn, "koutsuu\\updateSashimodoshiKtimestamp.sql", statement2);
    } catch (SQLException e) {
      super.endTransactionFalse(conn);
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
    }
    super.endTransactionTrue(conn);
  }

  /**
   * {@index 修正ボタン押下時のテーブル更新処理}
   * @param pStatement
   * @throws SQLException
   */
  public void updateKoutsuuSyuusei(Map<String, Object> pStatement) throws SQLException {
    //ステートメントの設定
    List<Object> statement1 = new ArrayList<>();//koutsuuテーブル
    List<Object> statement2 = new ArrayList<>();//ktimestampテーブル

    //set句
    statement1.add(pStatement.get(Koutsuu.COL_RIYOUHIDUKE));
    statement1.add(pStatement.get(Koutsuu.COL_KUKAN_S));
    statement1.add(pStatement.get(Koutsuu.COL_KUKAN_E));
    statement1.add(pStatement.get(Koutsuu.COL_KINGAKU));
    statement1.add(pStatement.get(Koutsuu.COL_BIKOU));
    statement1.add(pStatement.get(Koutsuu.COL_MODOSHIRIYUU));

    statement2.add(KCommon.SHINSEI);
    statement2.add(pStatement.get(KtimeStamp.COL_TIMESTAMP));

    //where句
    statement1.add(pStatement.get("selNo"));

    statement2.add(pStatement.get("selNo"));

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);
    try {
      super.startTransaction(conn);
      super.executeDMLMlt(conn, "koutsuu\\updateSyuuseiKoutsuu.sql", statement1);
      super.executeDMLMlt(conn, "koutsuu\\updateSyuuseiKtimestamp.sql", statement2);
    } catch (SQLException e) {
      super.endTransactionFalse(conn);
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
    }
    super.endTransactionTrue(conn);
  }

}
