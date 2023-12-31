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
import constents.Table.Login;
import constents.Table.Mail;
import constents.Table.User;
import constents.UserShousai;
import control.common.CastCommon;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaBean;
import model.MastaEntity;

public class MastaDAOSelect extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;
  
  /**
   * {@index mailテーブルの多重登録チェック用}
   * @param id
   * @param mailAddress
   * @return　既存のPKの組み合わせと一致する場合は1を返却、そうではない場合は0を返却
   */
  public int checkTajuuTouroku(int id,String mailAddress) {
    List<Object> statement = new ArrayList<>();
    statement.add(id);
    statement.add(mailAddress);
    return super.countSQL("masta\\mail\\checkTajuuTouroku.sql", statement);
  }
  
  /**
   * {@index ユーザーの一覧を取得}
   * @param fileName
   * @return
   */
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
        id = rs.getInt(User.COL_USERID);
        loginId = rs.getString(Login.COL_LOGINID);
        loginName = rs.getString(User.COL_USERNAME);
        kanriFlg = rs.getBoolean(User.COL_KANRIFLG);

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
  
  /**
   * {@index 編集するユーザーの情報を取得}
   * @param fileName
   * @param statement
   * @return　選択したユーザーのレコード
   */
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
    CastCommon castCommon = new CastCommon();

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
        id = rs.getInt(User.COL_USERID);
        loginId = rs.getString(Login.COL_LOGINID);
        loginName = rs.getString(User.COL_USERNAME);
        kanriFlg = rs.getBoolean(User.COL_KANRIFLG);
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
            sei,seiyomi, mei, meiyomi, castCommon.chgDateToStr(nyuusyaYMD), castCommon.chgDateToStr(taisyaYMD), seibetsu,
            castCommon.chgDateToStr(seinenngappi), syusshin, jyuusyo);
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
  
  /**
   * {@index メールアドレスマスタの情報取得用}
   * @param fileName　実行したいSQLファイルのパス（\SQL\以下を渡す）
   * @param statement　ない場合はNULLを指定
   * @return
   * @throws SQLException
   */
  public List<MastaBean> getMailTBL(String fileName,List<Object> statement) throws SQLException {

    List<MastaBean> result = new ArrayList<>();
    int id = 0;
    String userName = null;
    String mailAddress = null;

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
       
      //ステートメントの設定
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      if(statement != null) {
        pStmt.setObject(1,statement.get(0));
        pStmt.setObject(2,statement.get(1));
      }

      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      //結果表に格納されたレコードの内容をMastaBeanに設定
      while (rs.next()) {
        //mail
        id = rs.getInt(Mail.COL_USERID);
        userName = rs.getString(User.COL_USERNAME);
        mailAddress = rs.getString(Mail.COL_MAILADDRESS);
        MastaBean bean = new MastaBean(id, userName,mailAddress);
        result.add(bean);
      }
    } catch (SQLException e) {
      throw e;
    } finally {
      DBAccess.super.closeDB(conn);
    }

    sqlPath = Common.SQL_FILE_PATH;

    return result;
  }
}
