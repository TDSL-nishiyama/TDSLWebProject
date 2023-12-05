package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import control.common.CheckCommon;
import model.MastaBean;

public class UserUpdBL {

  /**
   * {@index 管理フラグ変更確認}
   * @param loginId　ログインしているユーザーID
   * @param chgId　変更しようとしているユーザーID
   * @param kanriFlg　変更前(0)/後(1)の管理フラグ
   * @return
   */
  public boolean checkKanriFlg(int loginId, int chgId, boolean[] kanriFlg) {
    boolean result = false;

    CheckCommon checkCommon = new CheckCommon();
    //ログインIDと変更しようとしているIDが一致している場合
    if (checkCommon.checkLoginIdEqualsId(loginId, chgId)) {
      //管理フラグの値が不一致の場合、エラー
      if (kanriFlg[0] != kanriFlg[1]) {
        result = true;
      }
    }
    return result;
  }

  /**
   * {@index 実行結果の取得}
   * @param updKoumoku
   * @return
   */
  public List<MastaBean> selectUserList(Map<String, Object> updKoumoku) {

    List<MastaBean> result = new ArrayList<>();

    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getHensyuUser("getHensyuUser.sql", updKoumoku);

    return result;
  }

  /**
   * {@index ユーザー更新の実行}
   * @param updKoumoku
   * @param userId
   * @throws SQLException
   */
  public void updUserList(Map<String, Object> updKoumoku, int userId) throws SQLException {

    //ユーザー更新の実行
    MastaDAOInsertUpdate mastaDaoUpd = new MastaDAOInsertUpdate();
    mastaDaoUpd.updUserALL(updKoumoku, userId);

  }
}
