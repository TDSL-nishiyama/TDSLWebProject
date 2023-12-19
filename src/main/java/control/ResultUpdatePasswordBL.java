package control;

import java.sql.SQLException;

public class ResultUpdatePasswordBL {

  /**
   * {@ index ID規約チェック（先頭４文字がkari~はNG）}
   * @param loginId
   * @return
   */
  public boolean checkLoginIdConditions(String loginId) {
    boolean result = true;

    //先頭文字がkari~はNG（不正ID作成の防止のため）
    if (loginId.substring(0, 4).equals("kari")) {
      result = false;
    }

    return result;
  }
  
  /**
   * {@index loginテーブルに対し新規でユーザーの登録を行う}
   * @param loginIdBefore
   * @param pLoginId
   * @param pPassword
   * @throws SQLException
   */
  public void updateUserPassword(String loginIdBefore, String pLoginId, String pPassword) throws SQLException {

    //新規ログインIDとパスワードの登録
    //TODO  パスワードのハッシュ化
    LoginDAOInsertUpdate insDAO = new LoginDAOInsertUpdate();
    insDAO.updateUserAndPassword(loginIdBefore, pLoginId, pPassword);
    
  }
  
  /**
   * {@index loginテーブルに対しパスワードの更新を行う}
   * @param pUserId
   * @param pPassword
   * @throws SQLException
   */
  public void updatePassword(String pUserId, String pPassword) throws SQLException {

      //パスワードの更新
      //TODO パスワードのハッシュ化
      LoginDAOInsertUpdate upDAO = new LoginDAOInsertUpdate();
      upDAO.updatePassword(pUserId, pPassword);
  
  }
}
