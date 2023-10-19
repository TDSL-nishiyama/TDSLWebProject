package control;

import model.MastaEntity;

public class UserAddBL {
	
	public void userAdd(String userName,boolean kanriFlg){
	
	int userid = 0;
	String loginName = "";
	String loginPassword = "";
	
	//ID（）連番・仮登録用ID・仮登録パスワードの作成
	LoginDAO loginDAO = new LoginDAO();
	userid = Integer.parseInt(loginDAO.maxUserId()) + 1;   
	loginName = "kari" + String.valueOf(userid);
	loginPassword = "tdsl";
	
	MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate(); 
	
	MastaEntity mastaEntity = new MastaEntity(userid, userName, kanriFlg,loginName,loginPassword);
	
	//ユーザーテーブル登録
	useraddDAOInsUp.InsertUser(mastaEntity);
	
	//ログインテーブル仮登録
	useraddDAOInsUp.InsertLogin(mastaEntity);
	}
}
