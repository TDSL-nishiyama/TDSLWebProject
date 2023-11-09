package control;

import java.util.ArrayList;
import java.util.List;

import model.MastaEntity;

public class UserUpdBL {
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultUserList(UserUpdBL userIchiranBL,int pID) {
    
    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran(mastaDAOsel,pID);

    return result;
  }
}
