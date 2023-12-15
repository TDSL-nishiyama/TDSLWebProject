package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.MastaBean;

/**
 * @see メールアドレスマスタ関連BL
 */
public class MailBL {
  
  /**
   * {@index 必須項目チェック}
   * @param id
   * @param mailAddress
   * @return true=エラーあり false=エラーなし
   */
  public boolean checkHissuKoumoku(String id,String mailAddress) {
    boolean result = false;
    
    if(id == null || id.equals("") || mailAddress == null || mailAddress.equals("")) {
      result = true;
    }
    
    return result;
  }
  
  /**
   * {@index 多重登録チェック}
   * @param id
   * @param mailAddress
   * @return true=エラーあり false=エラーなし
   */
  public boolean checkTajuuTouroku(int id,String mailAddress) {
    boolean result = false;
    
    MastaDAOSelect dao = new MastaDAOSelect();
    if(dao.checkTajuuTouroku(id, mailAddress)!=0) {
      result = true;
    }
    
    return result;
  }
  
  /**
   * {@index メールテーブルの全件取得用}
   * @return
   * @throws SQLException
   */
  public List<MastaBean> getMailTBL() throws SQLException{
    List<MastaBean> result = new ArrayList<>();
    
    MastaDAOSelect dao = new MastaDAOSelect();
    result = dao.getMailTBL("masta\\mail\\getMail.sql",null);
    
    return result;
  }
  
  /**
   * {@index メールテーブルの指定ID取得用}
   * @param gamenInfo key:selId=指定のID　key:mailAddress=指定のメールアドレス
   * @return
   * @throws SQLException
   */
  public List<MastaBean> getMailTBL(Map<String,Object> gamenInfo) throws SQLException{
    List<MastaBean> result = new ArrayList<>();
    List<Object> statement = new ArrayList<>();
    MastaDAOSelect dao = new MastaDAOSelect();
    
    statement.add(gamenInfo.get("selId"));
    statement.add(gamenInfo.get("mailAddress"));
    
    result = dao.getMailTBL("masta\\mail\\getMailUni.sql",statement);
    
    return result;
  }
  
  /**
   * {@index メールテーブルの登録処理}
   * @param gamenInfo
   * @throws SQLException
   */
  public void insertMailTBL(Map<String,Object> gamenInfo) throws SQLException {
    MastaDAOInsertUpdate dao = new MastaDAOInsertUpdate();
    dao.insertMail(gamenInfo);
  }
  
  /**
   *  {@index メールテーブルの更新処理}
   * @param gamenInfo
   * @throws SQLException
   */
  public void updateMailTBL(Map<String,Object> gamenInfo) throws SQLException {
    MastaDAOInsertUpdate dao = new MastaDAOInsertUpdate();
    dao.updateMail(gamenInfo);
  }
  
  /**
   *  {@index メールテーブルの削除処理}
   * @param id
   * @param mailAddress
   * @throws SQLException
   */
  public void deleteMailTBL(int id,String mailAddress) throws SQLException {
    MastaDAOInsertUpdate dao = new MastaDAOInsertUpdate();
    dao.deleteMail(id,mailAddress);
  }

}
