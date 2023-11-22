package control;

import java.util.ArrayList;
import java.util.List;

public class LoginBL{

  /**
   * {@index}初回ログインチェック用
   * @param ログインID
   * @return 要素0 error→エラー画面　kari→パスワード登録更新画面 true→後続処理
   * @return 要素1 loginテーブルのログイン名に紐づくloginテーブルのIDを取得
  **/
  public String[] checkLoginShokai(String pLoginId) {

    //要素0 error→エラー画面　kari→パスワード登録更新画面 true→後続処理
    //要素1 loginテーブルのログイン名に紐づくloginテーブルのIDを取得
    String[] result = { "error", "" };

    //IDは5桁以上のためエラー（画面からの登録はできないが一応）
    if (pLoginId.length() > 4) {
      //IDが仮IDだった場合、パスワード更新登録画面に遷移
      if (pLoginId.substring(0, 4).equals("kari")) {
        result[0] = "toPassword";
        //loginテーブルid項目の取得（Sessionを保持していない画面のためDBより取得）
        LoginDAO loginDAO = new LoginDAO();
        result[1] = String.valueOf(loginDAO.findLoginIdtoId(pLoginId));
        //それ以外の場合は後続処理に移行
      } else {
        result[0] = "true";
      }
    }
    return result;
  }

  /**
   * {@index}ログインID存在チェック
   * @param ログインID
   * @return true→エラーあり　false→エラーなし
   **/
  public boolean checkLoginId(String pLoginId) {

    boolean result = false;

    LoginDAO loginDAO = new LoginDAO();

    // IDがDB内に存在しなかった場合エラー画面に遷移
    if (!(loginDAO.findLoginId(pLoginId) == 0)) {
      result = true;
    }
    return result;
  }
  
  /**
   * {@index}ログインパスワードとIDの紐づきチェック
   * @param pLoginId ログインID
   * @param pLoginPassword ログインパスワード
   * @return true→エラーあり　false→エラーなし
   **/
  public boolean checkLoginIdAndPassword(String pLoginId,
      String pLoginPassword) {

    boolean result = false;

    LoginDAO loginDAO = new LoginDAO();

    // 入力したパスワードがDB内のパスワードと異なる場合エラー画面に遷移
    List<Object> loginIdPassList = new ArrayList<Object>();
    loginIdPassList.add(pLoginId);
    loginIdPassList.add(pLoginPassword);

    if (loginDAO.findLoginIdAndPassword(pLoginId, pLoginPassword).equals(loginIdPassList)) {
      result = true;
    }
    return result;
  }

  /**
   * {@index}ログインID重複チェック
   * @param ログインID
   * @return true→エラーあり　false→エラーなし
   **/
  public boolean checkDuplicationLoginId(String pLoginId) {

    boolean result = true;
    int resultDB = 0;

    LoginDAO loginDAO = new LoginDAO();
    resultDB = loginDAO.countLoginId(pLoginId);

    // IDが重複している場合、エラー画面に遷移
    if (resultDB > 0) {
      result = false;
    }
    return result;
  }
  
  /**
   * {@index}ログインID桁数チェック
   * @param ログインID
   * @return true→エラーあり　false→エラーなし
   * @see IDは5桁以上
   **/
  //ログインID桁数チェック
  public boolean checkLoginIdLength(String pLoginId) {
    
    boolean result = true;
    
    // IDが5桁未満の場合、エラー画面に遷移
    if (pLoginId.length() < 5) {
      result = false;
    }
    return result;
  }
  
  /**
   * {@index}セッション情報取得
   * @param loginid ログインID
   * @return セッション情報
   **/
  public List<Object> getSessionInfo(String loginid){
    
    List<Object> result = new ArrayList<>();
    LoginDAO loginDAO = new LoginDAO();
    result = loginDAO.getSessionInfo(loginid);
    
    return result;
  }
}
