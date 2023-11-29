package control;

import java.sql.SQLException;

import constents.Const.ERRORMSG;
import control.common.DAOCommon;

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
  
  public void updateUserPassword(String loginIdBefore,String pLoginId,String pPassword) throws SQLException{

    DAOCommon dao = new DAOCommon();
    try {
      //トランザクション開始
      dao.startTransaction();
      //新規ログインIDとパスワードの登録
      LoginDAOInsertUpdate insDAO = new LoginDAOInsertUpdate();
      insDAO.updateUserAndPassword(loginIdBefore,pLoginId, pPassword);
    }catch(SQLException e) {
      dao.endTransactionFalse();
      throw new SQLException(ERRORMSG.DBERROR);
    }
    dao.endTransactionTrue();
  }
  
  public void updatePassword(String pUserId,String pPassword) throws SQLException{
    
    DAOCommon dao = new DAOCommon();
    try {
      //トランザクション開始
      dao.startTransaction();
      //パスワードの更新
      LoginDAOInsertUpdate upDAO = new LoginDAOInsertUpdate();
      upDAO.updatePassword(pUserId,pPassword);
    }catch(SQLException e) {
      dao.endTransactionFalse();
      throw new SQLException();
    }
    dao.endTransactionTrue();
  }
  
}
