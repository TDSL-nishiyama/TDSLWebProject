package control;

import java.util.ArrayList;
import java.util.List;

import constents.Table.Login;
import constents.Table.User;
import control.common.DAOCommon;
import control.common.DBAccess;

public class LoginDAO extends DAOCommon implements DBAccess {
  
  /**
   * {@index} loginidからIDを検索する（セッションでIDを保持していない画面での利用を想定）
   * @param pLoginId ログインID
   * @return pLoginIdに紐づくid
   */
  public List<Object> findLoginIdtoId(String pLoginId) {

    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<>();
    List<Object> result = new ArrayList<>();

    column.add(Login.COL_USERID);

    statement.add(pLoginId);

    result = selectSQL("findLoginIdtoId.sql", column, statement);

    return result;
  }
  /**
   * {@index} IDの最大値取得用
   * @return userテーブルのid項目の最大値
   */
  public int maxUserId() {

    int resultCount = 0;
    resultCount = super.countSQL("maxUserId.sql", null);

    return resultCount;
  }

  /**
   * {@index} loginidの重複チェック用
   * @param pLoginId ログインID
   * @return 1=ID重複あり
   */
  public int countLoginId(String pLoginId) {

    List<Object> statement = new ArrayList<>();
    int result = 0;

    statement.add(pLoginId);

    result = countSQL("countLoginId.sql", statement);

    return result;

  }
  
  /**
   * {@index} ログインID検索用
   * @param pLoginId ログインID
   * @return 0=IDなし
   */
  public int findLoginId(String pLoginId) {

    List<Object> statement = new ArrayList<>();
    int result = 0;

    statement.add(pLoginId);

    result = countSQL("findLoginId.sql", statement);

    return result;

  }

  /**
   * {@index} IDパスワード一致チェック用
   * @param pLoginId ログインID
   * @param pPassword ログインパスワード
   * @return
   */
  public List<Object> findLoginIdAndPassword(String pLoginId, String pPassword) {

    List<Object> result = new ArrayList<Object>();
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<Object>();

    column.add(Login.COL_LOGINID);
    column.add(Login.COL_LOGINPASSWORD);

    statement.add(pLoginId);
    statement.add(pPassword);

    result = super.selectSQL("findLoginIdAndPassword.sql", column, statement);

    return result;
  }

  /**
   * {@index} セッション情報の取得
   * @param pLoginId ログインID
   * @return 実行結果(List<Object>)
   */
  public List<Object> getSessionInfo(String pLoginId) {

    List<Object> result = new ArrayList<>();
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<>();

    column.add(User.COL_USERID);
    column.add(Login.COL_LOGINID);
    column.add(User.COL_USERNAME);
    column.add(User.COL_KANRIFLG);

    statement.add(pLoginId);

    result = selectSQL("getSessionInfo.sql", column, statement);

    return result;
  }

}
