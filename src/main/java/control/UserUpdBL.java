package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.common.CheckCommon;
import control.common.DAOCommon;
import model.MastaBean;

public class UserUpdBL {
  //ID存在確認
  public boolean userUpdCheck(int userIdDel) {

    boolean result = true;

    CheckCommon checkCommon = new CheckCommon();

    //ユーザーID確認
    int i = checkCommon.checkUserIdAll(userIdDel);

    //カウント結果が0の場合IDは存在しない
    if (i == 0) {
      result = false;
    }

    return result;
  }
  
  /**
   *{@index 必須項目確認} 
   * @param chkKoumoku チェックしたい項目名（対象画面情報のkey/value）
   * @param hissuKoumoku 必須項目名（KEY）/必須項目の論理名(VALUE）
   * @return key:errflg エラーの可否 true = 正常　false = 異常
   * @return key:errMsgKoumoku hissuKoumokuの中でエラーとなった項目
   */
  public Map<String, Object> checkHissu(Map<String, Object> chkKoumoku, Map<String, String> hissuKoumoku) {
    Map<String, Object> result = new HashMap<>();
    boolean errflg = true;
    String errMsgKoumoku = null;
    result.put("errflg", errflg);
    result.put("errMsgKoumoku", errMsgKoumoku);
    CheckCommon checkCommon = new CheckCommon();

    //画面項目のvalue値分検索、必須項目のkey値と一致した場合、
    //必須チェックを行いエラーだった場合、必須項目のValueをメッセージとして格納
    loop: for (String gamen : chkKoumoku.keySet()) {
      for (String hissu : hissuKoumoku.keySet())
        if (gamen == hissu) {
          errflg = checkCommon.checkBlankOrNULL((String) chkKoumoku.get(gamen));
          result.put("errflg", errflg);
          if (errflg == false) {
            errMsgKoumoku = hissuKoumoku.get(hissu);
            result.put("errMsgKoumoku", errMsgKoumoku);
            break loop;
          } else {
            break;
          }
        }
    }

    return result;
  }

  //結果表の取得
  public List<MastaBean> selectUserList(Map<String, Object> updKoumoku) {

    List<MastaBean> result = new ArrayList<>();

    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getHensyuUser("getHensyuUser.sql", updKoumoku);

    return result;
  }

  //ユーザー更新の実行
  public void updUserList(Map<String, Object> updKoumoku,int userId) throws SQLException {

    MastaDAOInsertUpdate mastaDaoUpd = new MastaDAOInsertUpdate();
    DAOCommon dao = new DAOCommon();
    try {
      //トランザクション開始
      dao.startTransaction();
      //更新の実行
      mastaDaoUpd.updUser(updKoumoku,userId);
      mastaDaoUpd.updateUserShousai(updKoumoku,userId);
    } catch (SQLException e) {
      dao.endTransactionFalse();
      throw new SQLException();
    }
    dao.endTransactionTrue();
  }
}
