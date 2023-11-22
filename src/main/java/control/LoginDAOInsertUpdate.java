package control;

import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;
import control.common.DBAccess;

public class LoginDAOInsertUpdate extends DAOCommon implements DBAccess {

  public void updateUserAndPassword(String loginIdBefore,String pLoginIdAfter, String pLoginPassword) {
    
    List<Object> statement = new ArrayList<Object>();
    statement.add(pLoginIdAfter);
    statement.add(pLoginPassword);
    statement.add(loginIdBefore);
    
    super.executeDML("updateUserAndPassword.sql", statement);
    
  }
  

  public void updatePassword(String pLoginId, String pLoginPassword) {

    List<Object> statement = new ArrayList<Object>();
    statement.add(pLoginPassword);
    statement.add(pLoginId);
    
    super.executeDML("updatePassword.sql", statement);
  }
}
