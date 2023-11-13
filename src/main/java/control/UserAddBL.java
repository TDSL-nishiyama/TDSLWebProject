package control;

import java.util.Map;

import model.MastaEntity;
import model.SyainJouhouEntity;

public class UserAddBL {
  
  public void userAdd(Map<String,String> input){
  
  int userid = 0;
  String loginName = "";
  String loginPassword = "";
  boolean kanriFlg = false;
  
  //ID（）連番・仮登録用ID・仮登録パスワードの作成
  LoginDAO loginDAO = new LoginDAO();
  userid = Integer.parseInt(loginDAO.maxUserId()) + 1;   
  loginName = "kari" + String.valueOf(userid);
  loginPassword = "tdsl";
  kanriFlg = Boolean.valueOf(input.get("kanriflg"));
  
  //コンスタラクタでEntityに値を設定
  MastaEntity mastaEntity = new MastaEntity(userid, loginName, kanriFlg, loginPassword);
  SyainJouhouEntity syainJouhouEntity = new SyainJouhouEntity();
  
  //各種テーブルへの登録
  MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate(); 
  //ユーザーテーブル登録
  useraddDAOInsUp.InsertUser(mastaEntity);
  
  //ログインテーブル仮登録
  useraddDAOInsUp.InsertLogin(mastaEntity);
  
  //ユーザー詳細テーブル仮登録
  useraddDAOInsUp.InsertUserShoisai(syainJouhouEntity);
  }
}
