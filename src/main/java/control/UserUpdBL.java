package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.MastaBean;
import model.MastaEntity;

public class UserUpdBL {
  //ID存在確認
  public boolean userUpdCheck(int userIdDel) {

    boolean result = true;

    MastaDAOSelect mastaDAOSelect = new MastaDAOSelect();
    MastaEntity mastaEntity = new MastaEntity(userIdDel);

    //ユーザーID確認
    int i = mastaDAOSelect.checkUserId(mastaEntity);

    //カウント結果が0の場合IDは存在しない
    if (i == 0) {
      result = false;
    }

    return result;
  }
  
  //結果表の取得
  public List<MastaBean> selectUserList(Map<String,Object> updKoumoku) {
    
    List<MastaBean> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getHensyuUser("getHensyuUser.sql",updKoumoku);
    
    return result;
  }
  
  //ユーザー更新の実行
  public void updUserList(Map<String,Object> updKoumoku){
	  
	  MastaDAOInsertUpdate mastaDaoUpd = new MastaDAOInsertUpdate();
	  mastaDaoUpd.updUser(updKoumoku);
	  
  }
}
