package control;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constents.Const.Common;
import constents.UserShousai;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaBean;
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

  public List<MastaBean> getHensyuUser(String fileName, Map<String, Object> statement) {

    List<MastaBean> returnList = new ArrayList<>();
    int id = 0;
    String loginId = "";
    String loginName = "";
    boolean kanriFlg = false;
    String sei = null;
    String seiyomi = null;
    String mei = null;
    String meiyomi = null;
    Date nyuusyaYMD = null;
    Date taisyaYMD = null;
    String seibetsu = null;
    Date seinenngappi = null;
    String syusshin = null;
    String jyuusyo = null;

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

      //結果表に格納されたレコードの内容をMastaBeanに設定
      while (rs.next()) {
        //user・login
        id = rs.getInt("id");
        loginId = rs.getString("loginid");
        loginName = rs.getString("name");
        kanriFlg = rs.getBoolean("kanriFlg");
        //usershousai
        sei = rs.getString(UserShousai.COL_SEI);
        seiyomi = rs.getString(UserShousai.COL_SEI_YOMI);
        mei = rs.getString(UserShousai.COL_MEI);
        meiyomi = rs.getString(UserShousai.COL_MEI_YOMI);
        nyuusyaYMD = rs.getDate(UserShousai.COL_NYUUSYAYMD);
        taisyaYMD = rs.getDate(UserShousai.COL_TAISYAYMD);
        seibetsu = rs.getString(UserShousai.COL_SEIBETSU);
        seinenngappi = rs.getDate(UserShousai.COL_SEINENGAPPI);
        syusshin = rs.getString(UserShousai.COL_SYUSSHIN);
        jyuusyo = rs.getString(UserShousai.COL_JYUUSYO);

        MastaBean bean = new MastaBean(id, loginName, kanriFlg, loginId, loginName, 
            sei,seiyomi, mei, meiyomi, nyuusyaYMD, taisyaYMD, seibetsu,
            seinenngappi, syusshin, jyuusyo);
        returnList.add(bean);
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
