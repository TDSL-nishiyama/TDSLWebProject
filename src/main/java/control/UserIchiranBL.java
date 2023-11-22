package control;

import java.util.ArrayList;
import java.util.List;

import model.MastaEntity;

public class UserIchiranBL {
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultUserList(UserIchiranBL userIchiranBL) {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("userIchiran.sql");

    return result;
  }
  
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultUserListAll(UserIchiranBL userIchiranBL) {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("userIchiranAll.sql");

    return result;
  }
  
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultKaritourokuUserList(UserIchiranBL userIchiranBL) {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("karitouroku.sql");

    return result;
  }
}
