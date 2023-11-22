package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.MastaBean;

public class UserUpdBL {
  //実行結果をサーブレットに戻す
  public List<MastaBean> selectUserList(UserUpdBL userIchiranBL,Map<String,Object> updKoumoku) {
    
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
