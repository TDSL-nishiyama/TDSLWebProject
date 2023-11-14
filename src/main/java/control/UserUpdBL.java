package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.MastaEntity;

public class UserUpdBL {
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultUserList(UserUpdBL userIchiranBL,Map<String,String> updKoumoku) {
    
    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getHensyuUser(mastaDAOsel,updKoumoku);
    
    return result;
  }
  
  //ユーザー更新の実行
  public void updUserList(Map<String,String> updKoumoku){
	  
	  MastaDAOInsertUpdate mastaDaoUpd = new MastaDAOInsertUpdate();
	  mastaDaoUpd.updUser(updKoumoku);
	  
  }
}
