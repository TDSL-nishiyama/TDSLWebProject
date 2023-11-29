package control;

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

  public void InsertUser(MastaEntity pEntity) throws SQLException {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());
    statement.add(pEntity.getUserName());
    statement.add(pEntity.getKanriFlg());

    super.executeDML("insertUser.sql", statement);

  }

  public void InsertLogin(MastaEntity pEntity) throws SQLException {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());
    statement.add(pEntity.getLoginid());
    statement.add(pEntity.getLoginpassword());

    super.executeDML("insertLogin.sql", statement);

  }

  public void InsertUserShoisai(SyainJouhouEntity pEntity) throws SQLException {
    List<Object> statement = new ArrayList<>();
    
    statement.add(pEntity.getId());
    statement.add(pEntity.getSei());
    statement.add(pEntity.getSei_yomi());
    statement.add(pEntity.getMei());
    statement.add(pEntity.getMei_yomi());
    statement.add(pEntity.getNyuusyaYMD());
    statement.add(pEntity.getTaisytaYMD());
    statement.add(pEntity.getSeibetsu());
    statement.add(pEntity.getSeinenngappi());
    statement.add(pEntity.getSyusshin());
    statement.add(pEntity.getJuusyo());
    
    super.executeDML("insertUserShousai.sql", statement);
  }

  public void delUser(MastaEntity pEntity) throws SQLException {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());

    super.executeDML("delUser.sql", statement);
  }

  public void delLogin(MastaEntity pEntity) throws SQLException {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());

    super.executeDML("delLogin.sql", statement);

  }

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

}
