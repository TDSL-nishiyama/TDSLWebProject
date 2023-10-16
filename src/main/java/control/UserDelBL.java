package control;

import model.MastaEntity;

public class UserDelBL {
	
	public void userDel(int userId,String sakujo){
	
	MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate(); 
	
	MastaEntity useraddentity = new MastaEntity(userId,sakujo);
	
	//ユーザーテーブル削除（論理削除）
	useraddDAOInsUp.delUser(useraddentity);
	
	//ログインテーブル削除（論理削除）
	useraddDAOInsUp.delLogin(useraddentity);
	}
}
