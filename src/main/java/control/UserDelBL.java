package control;

import model.MastaEntity;

public class UserDelBL {
	
	public boolean userDelCheck(int userId,int userIdDel) {
		
		boolean result = true;
		
		if(userId == userIdDel) {
			result = false;
		}
		
		return result;
	}
	
	public void userDel(int userId){
	
	MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate(); 
	
	MastaEntity mastaEntity = new MastaEntity(userId);
	
	//ユーザーテーブル削除（論理削除）
	useraddDAOInsUp.delUser(mastaEntity);
	
	//ログインテーブル削除（論理削除）
	useraddDAOInsUp.delLogin(mastaEntity);
	}
}
