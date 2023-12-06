package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import constents.Const.Common;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.SyainJouhouEntity;

public class SyainJouhouDAO extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@index} 社員情報の情報を取得
   * @param fileName 実行したいSQLファイルの名前
   * @param column 取得したいカラム名
   * @param statement ステートメント（不要な場合、NULLを設定）
   * @param kanriFlg 管理者フラグ（1=管理者、0=一般ユーザー）
   * @return selectの結果
   */
  public List<SyainJouhouEntity> selectSQL(String fileName, List<String> column, List<Object> statement,
      boolean kanriFlg) {

    List<SyainJouhouEntity> result = new ArrayList<SyainJouhouEntity>();
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
    if (kanriFlg == false) {
      try {
        //発行
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

        int id = 0;
        String sei = null;
        String seiyomi = null;
        String mei = null;
        String meiyomi = null;
        Date nyuusyaYMD = null;
        String syusshin = null;
        String juusyo = null;

        final int ID = 0;
        final int SEI = 1;
        final int SEI_YOMI = 2;
        final int MEI = 3;
        final int MEI_YOMI = 4;
        final int NYUUSYAYMD = 5;
        final int SYUSSHIN = 6;
        final int JUUSYO = 7;

        //格納
        while (rs.next()) {
          id = rs.getInt(column.get(ID));
          sei = rs.getString(column.get(SEI));
          seiyomi = rs.getString(column.get(SEI_YOMI));
          mei = rs.getString(column.get(MEI));
          meiyomi = rs.getString(column.get(MEI_YOMI));
          nyuusyaYMD = rs.getDate(column.get(NYUUSYAYMD));
          syusshin = rs.getString(column.get(SYUSSHIN));
          juusyo = rs.getString(column.get(JUUSYO));
          SyainJouhouEntity entity = new SyainJouhouEntity(id, sei, seiyomi,mei,meiyomi, nyuusyaYMD, syusshin,juusyo);
          result.add(entity);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      try {
        //発行
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

        int id = 0;
        String sei = null;
        String seiyomi = null;
        String mei = null;
        String meiyomi = null;
        Date nyuusyaYMD = null;
        Date taisyaYMD = null;
        String seibetsu = null;
        Date seinenngappi = null;
        String syusshin = null;
        String juusyo = null;

        final int ID = 0;
        final int SEI = 1;
        final int SEI_YOMI = 2;
        final int MEI = 3;
        final int MEI_YOMI = 4;
        final int NYUUSYAYMD = 5;
        final int TAISYAYMD = 6;
        final int SEIBETSU = 7;
        final int SEINENNGAPPI = 8;
        final int SYUSSHIN = 9;
        final int JUUSYO = 10;

        //格納
        while (rs.next()) {
          id = rs.getInt(column.get(ID));
          sei = rs.getString(column.get(SEI));
          seiyomi = rs.getString(column.get(SEI_YOMI));
          mei = rs.getString(column.get(MEI));
          meiyomi = rs.getString(column.get(MEI_YOMI));
          nyuusyaYMD = rs.getDate(column.get(NYUUSYAYMD));
          taisyaYMD = rs.getDate(column.get(TAISYAYMD));
          seibetsu = rs.getString(column.get(SEIBETSU));
          seinenngappi = rs.getDate(column.get(SEINENNGAPPI));
          syusshin = rs.getString(column.get(SYUSSHIN));
          juusyo = rs.getString(column.get(JUUSYO));

          SyainJouhouEntity entity = new SyainJouhouEntity(id, sei, seiyomi, mei, meiyomi, nyuusyaYMD, taisyaYMD,
              seibetsu, seinenngappi, syusshin, juusyo);
          result.add(entity);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        sqlPath = Common.SQL_FILE_PATH;
        //DB切断
        DBAccess.super.closeDB(conn);
      }
    }
    return result;
  }

  /**
   * {@index} 社員情報の更新を実施(usershousaiテーブル)
   * @param fileName 実行したいSQLファイルの名前
   * @param updKoumoku アップデートしたい項目
   * @throws SQLException 
   */
  public void updateSyainJouhou(String fileName, List<Object> updKoumoku) throws SQLException {

    super.executeDML(fileName, updKoumoku);
    sqlPath = Common.SQL_FILE_PATH;

  }
}
