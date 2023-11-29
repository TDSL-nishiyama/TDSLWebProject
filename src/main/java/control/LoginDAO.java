package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;
import control.common.DBAccess;

public class LoginDAO extends DAOCommon implements DBAccess {

  public List<Object> findLoginIdtoId(String pLoginId)  {

    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<>();
    List<Object> result = new ArrayList<>();

    column.add("id");

    statement.add(pLoginId);

    result = selectSQL("findLoginIdtoId.sql", column, statement);

    return result;
  }

  //IDの最大値取得用
  public int maxUserId() {

    int resultCount = 0;
    resultCount = super.countSQL("maxUserId.sql", null);

    return resultCount;
  }

  //loginidの重複チェック用
  public int countLoginId(String pLoginId) {

    List<Object> statement = new ArrayList<>();
    int result = 0;

    statement.add(pLoginId);

    result = countSQL("countLoginId.sql", statement);

    return result;

  }

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
   * @throws SQLException 
   */
  public List<Object> findLoginIdAndPassword(String pLoginId, String pPassword) {

    List<Object> result = new ArrayList<Object>();
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<Object>();

    column.add("loginid");
    column.add("password");

    statement.add(pLoginId);
    statement.add(pPassword);

    result = super.selectSQL("findLoginIdAndPassword.sql", column, statement);

    return result;
  }

  /**
   * {@index} セッション情報の取得
   * @param pLoginId ログインID
   * @return 実行結果(List<Object>)
   * @throws SQLException 
   */
  public List<Object> getSessionInfo(String pLoginId) {

    List<Object> result = new ArrayList<>();
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<>();

    column.add("id");
    column.add("loginid");
    column.add("name");
    column.add("kanriflg");

    statement.add(pLoginId);

    result = selectSQL("getSessionInfo.sql", column, statement);

    return result;
  }

}
