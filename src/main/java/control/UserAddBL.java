package control;

import model.UserAddEntity;

public class UserAddBL {
	
	public void userAdd(String userName,boolean kanriFlg){
	//TODO ユーザー重複チェック
		
	//TODO 連番の作成
	int userid = 6;
	String loginName = "kari6";
	String loginPassword = "tdsl";
			
	MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate(); 
	
	//ユーザーテーブル登録
	UserAddEntity useraddentity1 = new UserAddEntity(userid, userName, kanriFlg);
	useraddDAOInsUp.InsertUser(useraddentity1);
	
	//ログインテーブル仮登録
	UserAddEntity useraddentity2 = new UserAddEntity(userid,loginName,loginPassword);
	useraddDAOInsUp.InsertLogin(useraddentity2);
	}
}
