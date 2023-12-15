package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constents.UserShousai;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaEntity;
import model.SyainJouhouEntity;

public class MastaDAOInsertUpdate extends DAOCommon implements DBAccess {
  
  final String MASTA_MAIL = "masta\\mail\\";
  
  /**
   * {@index ユーザー新規挿入}
   * @param pMastaEntity
   * @param pSyainJouhouEntity
   * @throws SQLException
   */
  public void InsertUserAdd(MastaEntity pMastaEntity,SyainJouhouEntity pSyainJouhouEntity) throws SQLException{
    List<Object> statement1 = new ArrayList<>();
    statement1.add(pMastaEntity.getUserid());
    statement1.add(pMastaEntity.getUserName());
    statement1.add(pMastaEntity.getKanriFlg());
    
    List<Object> statement2 = new ArrayList<>();

    statement2.add(pMastaEntity.getUserid());
    statement2.add(pMastaEntity.getLoginid());
    statement2.add(pMastaEntity.getLoginpassword());
    
    List<Object> statement3 = new ArrayList<>();
    statement3.add(pSyainJouhouEntity.getId());
    statement3.add(pSyainJouhouEntity.getSei());
    statement3.add(pSyainJouhouEntity.getSei_yomi());
    statement3.add(pSyainJouhouEntity.getMei());
    statement3.add(pSyainJouhouEntity.getMei_yomi());
    statement3.add(pSyainJouhouEntity.getNyuusyaYMD());
    statement3.add(pSyainJouhouEntity.getTaisytaYMD());
    statement3.add(pSyainJouhouEntity.getSeibetsu());
    statement3.add(pSyainJouhouEntity.getSeinenngappi());
    statement3.add(pSyainJouhouEntity.getSyusshin());
    statement3.add(pSyainJouhouEntity.getJuusyo());
    
    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    try {
      conn = DBAccess.super.connectionDB(conn);
      //トランザクション開始
      super.startTransaction(conn);
      //userテーブルの更新
      super.executeDMLMlt(conn,"insertUser.sql", statement1);
      //loginテーブルの更新
      super.executeDMLMlt(conn,"insertLogin.sql", statement2);
      //usershousaiテーブルの更新
      super.executeDMLMlt(conn,"insertUserShousai.sql", statement3);
    } catch (SQLException e) {
      //ロールバック
      super.endTransactionFalse(conn);
      throw e;
    }
    //コミット
    super.endTransactionTrue(conn);
  }
  
  /**
   * {@index ユーザー削除（論理削除）}
   * @param id
   * @throws SQLException
   */
  public void delUserAndLogin(int id) throws SQLException {
    
    //ステートメントの設定
    List<Object> statement = new ArrayList<>();
    statement.add(id);
    
    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    try {
      conn = DBAccess.super.connectionDB(conn);
      //トランザクション開始
      super.startTransaction(conn);
      //userテーブルの更新
      super.executeDMLMlt(conn,"delUser.sql", statement);
      //loginテーブルの更新
      super.executeDMLMlt(conn,"delLogin.sql", statement);
    } catch (SQLException e) {
      //ロールバック
      super.endTransactionFalse(conn);
      throw e;
    }
    //コミット
    super.endTransactionTrue(conn);
  }
  
  /**
   * {@index} 社員情報の更新を実施(userテーブル)
   * @param fileName 実行したいSQLファイルの名前
   * @param updKoumoku アップデートしたい項目
   * @throws SQLException 
   */
  public void updUser(Map<String, Object> updKoumoku,int userId) throws SQLException {
    List<Object> statement = new ArrayList<>();

    statement.add(updKoumoku.get("userName"));
    statement.add(updKoumoku.get("kanriFlg"));
    statement.add(userId);
    
    super.executeDML("updUser.sql", statement);
  }
  
  /**
   * {@index} 社員情報の更新を実施(usershousaiテーブル)
   * @param fileName 実行したいSQLファイルの名前
   * @param updKoumoku アップデートしたい項目
   * @throws SQLException 
   */
  public void updateUserShousai(Map<String, Object> updKoumoku,int userId) throws SQLException {
    List<Object> statement = new ArrayList<>();
    
    /*実行クエリ
     * UPDATE usershousai SET sei=?,sei_yomi=?,mei=?,mei_yomi=?,nyuusyaYMD=?,seibetsu=?
     * ,seinenngappi=?,syusshin=?,juusyo=? where id = ?;
    */
    //SET句
    statement.add(updKoumoku.get(UserShousai.COL_SEI));
    statement.add(updKoumoku.get(UserShousai.COL_SEI_YOMI));
    statement.add(updKoumoku.get(UserShousai.COL_MEI));
    statement.add(updKoumoku.get(UserShousai.COL_MEI_YOMI));
    statement.add(updKoumoku.get(UserShousai.COL_NYUUSYAYMD));
    statement.add(updKoumoku.get(UserShousai.COL_SEIBETSU));
    statement.add(updKoumoku.get(UserShousai.COL_SEINENGAPPI));
    statement.add(updKoumoku.get(UserShousai.COL_SYUSSHIN));
    statement.add(updKoumoku.get(UserShousai.COL_JYUUSYO));
    //WHERE句
    statement.add(userId);
    
    super.executeDML("updUserShousai.sql", statement);
  }
  
  /**
   * {@index} 社員情報の更新を実施(user/usershousaiテーブル)
   * @param fileName 実行したいSQLファイルの名前
   * @param updKoumoku アップデートしたい項目
   * @throws SQLException 
   */
  public void updUserALL(Map<String, Object> updKoumoku,int userId) throws SQLException {
    List<Object> statement1 = new ArrayList<>();

    statement1.add(updKoumoku.get("userName"));
    statement1.add(updKoumoku.get("kanriFlg"));
    statement1.add(userId);
    
    List<Object> statement2 = new ArrayList<>();
    /*実行クエリ
     * UPDATE usershousai SET sei=?,sei_yomi=?,mei=?,mei_yomi=?,nyuusyaYMD=?,seibetsu=?
     * ,seinenngappi=?,syusshin=?,juusyo=? where id = ?;
    */
    //SET句
    statement2.add(updKoumoku.get(UserShousai.COL_SEI));
    statement2.add(updKoumoku.get(UserShousai.COL_SEI_YOMI));
    statement2.add(updKoumoku.get(UserShousai.COL_MEI));
    statement2.add(updKoumoku.get(UserShousai.COL_MEI_YOMI));
    statement2.add(updKoumoku.get(UserShousai.COL_NYUUSYAYMD));
    statement2.add(updKoumoku.get(UserShousai.COL_SEIBETSU));
    statement2.add(updKoumoku.get(UserShousai.COL_SEINENGAPPI));
    statement2.add(updKoumoku.get(UserShousai.COL_SYUSSHIN));
    statement2.add(updKoumoku.get(UserShousai.COL_JYUUSYO));
    //WHERE句
    statement2.add(userId);
    
    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    try {
      conn = DBAccess.super.connectionDB(conn);
      //トランザクション開始
      super.startTransaction(conn);
      //userテーブルの更新
      super.executeDMLMlt(conn,"updUser.sql", statement1);
      //usershousaiテーブルの更新
      super.executeDMLMlt(conn,"updUserShousai.sql", statement2);
    } catch (SQLException e) {
      //ロールバック
      super.endTransactionFalse(conn);
      throw e;
    }
    //コミット
    super.endTransactionTrue(conn);
  }
  
  public void insertMail(Map<String, Object> insKoumoku) throws SQLException {
    List<Object> statement = new ArrayList<Object>();
    statement.add(insKoumoku.get("id"));
    statement.add(insKoumoku.get("mailAddress"));
    statement.add(insKoumoku.get("timestamp"));
    
    String SQLPath = MASTA_MAIL + "InsMail.sql";
    
    super.executeDML(SQLPath, statement);
  }
  
  public void updateMail(Map<String, Object> updKoumoku)throws SQLException {
    List<Object> statement = new ArrayList<Object>();
    statement.add(updKoumoku.get("mailAddress"));
    statement.add(updKoumoku.get("timestamp"));
    statement.add(updKoumoku.get("id"));
    statement.add(updKoumoku.get("mailAddress"));
    
    String SQLPath = MASTA_MAIL + "updMail.sql";
    
    super.executeDML(SQLPath, statement);
  }
  
  public void deleteMail(int id,String mailAddress) throws SQLException{
    List<Object> statement = new ArrayList<Object>();
    statement.add(id);
    statement.add(mailAddress);
    
    String SQLPath = MASTA_MAIL + "delMail.sql";
    
    super.executeDML(SQLPath, statement);
  }

}
