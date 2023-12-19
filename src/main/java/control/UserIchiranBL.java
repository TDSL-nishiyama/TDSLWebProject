package control;

import java.util.ArrayList;
import java.util.List;

import model.MastaEntity;


public class UserIchiranBL {
  
  /**
   * {@ユーザー一覧を取得する}
   * @return　ユーザーの一覧（削除されたユーザーを除く）
   */
  public List<MastaEntity> resultUserList() {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("userIchiran.sql");

    return result;
  }
  
  /**
   * {@ユーザー一覧を取得する}
   * @return　ユーザーの一覧（削除されたユーザーを含む）
   */
  public List<MastaEntity> resultUserListAll() {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("userIchiranAll.sql");

    return result;
  }
  
  /**
   * {@ユーザー一覧を取得する}
   * @return　ユーザーの一覧（仮登録中のユーザーのみ）
   */
  public List<MastaEntity> resultKaritourokuUserList() {

    List<MastaEntity> result = new ArrayList<>();
    
    //DAOクラスのインスタンス化
    MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
    result = mastaDAOsel.getUserIchiran("karitouroku.sql");

    return result;
  }
}
