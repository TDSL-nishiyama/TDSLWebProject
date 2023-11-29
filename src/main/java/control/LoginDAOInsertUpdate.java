package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;
import control.common.DBAccess;

public class LoginDAOInsertUpdate extends DAOCommon implements DBAccess {

  /**
   * {@index}　ログインID・パスワードの更新用
   * @param loginIdBefore　変更前ログインID
   * @param pLoginIdAfter　変更後ログインID
   * @param pLoginPassword　変更後のパスワード
   * @throws SQLException
   */
  public void updateUserAndPassword(String loginIdBefore, String pLoginIdAfter, String pLoginPassword)
      throws SQLException {

    List<Object> statement = new ArrayList<Object>();
    statement.add(pLoginIdAfter);
    statement.add(pLoginPassword);
    statement.add(loginIdBefore);

    super.executeDML("updateUserAndPassword.sql", statement);

  }

  /**
   * {@index}　パスワードの更新用
   * @param pLoginId　ログインID
   * @param pLoginPassword　変更後のパスワード
   * @throws SQLException
   */
  public void updatePassword(String pLoginId, String pLoginPassword) throws SQLException {

    List<Object> statement = new ArrayList<Object>();
    statement.add(pLoginPassword);
    statement.add(pLoginId);

    super.executeDML("updatePassword.sql", statement);
  }
}
