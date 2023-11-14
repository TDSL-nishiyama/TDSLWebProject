package control;

public class ResultUpdatePasswordBL {
  
  //IDの規約チェック
  public boolean checkLoginIdConditions(String loginId) {
    boolean result = true;
    
    //先頭文字がkari~はNG（不正ID作成の防止のため）
    if(loginId.substring(0, 4).equals("kari")) {
      result = false;
    }
    
    return result;
  }
  
  public void updateUserPassword(String pUserId,String pLoginname,String pPassword){

    //新規ログインIDとパスワードの登録
    LoginDAOInsertUpdate insDAO = new LoginDAOInsertUpdate();
    insDAO.updateUserPassword(pUserId, pLoginname, pPassword);
  }
  
  public void updatePassword(String pUserId,String pPassword){
    
    //パスワードの更新
    LoginDAOInsertUpdate upDAO = new LoginDAOInsertUpdate();
    upDAO.updatePassword(pUserId,pPassword);
  }
  
}
