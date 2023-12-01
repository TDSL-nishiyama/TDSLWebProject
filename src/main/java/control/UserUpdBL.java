package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import control.common.CheckCommon;
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

  //結果表の取得
  public List<MastaBean> selectUserList(Map<String, Object> updKoumoku) {

    List<MastaBean> result = new ArrayList<>();

    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getHensyuUser("getHensyuUser.sql", updKoumoku);

    return result;
  }

  //ユーザー更新の実行
  public void updUserList(Map<String, Object> updKoumoku, int userId) throws SQLException {

    //ユーザー更新の実行
    MastaDAOInsertUpdate mastaDaoUpd = new MastaDAOInsertUpdate();
    mastaDaoUpd.updUserALL(updKoumoku, userId);
    mastaDaoUpd.updUserALL(updKoumoku, userId);

  }
}
