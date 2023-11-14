package control;

import model.MastaEntity;

public class UserDelBL {

	//ID存在確認
	public boolean userDelCheck(int userIdDel) {

		boolean result = true;

		MastaDAOSelect mastaDAOSelect = new MastaDAOSelect();
		MastaEntity mastaEntity = new MastaEntity(userIdDel);

		//ユーザーID確認
		int i = Integer.parseInt(mastaDAOSelect.checkUserId(mastaEntity));

		//カウント結果が0の場合IDは存在しない
		if (i == 0) {
			result = false;
		}

		return result;
	}

	//ログインID-削除ID一致確認
	public boolean userDelCheck(int userId, int userIdDel) {

		boolean result = true;

		if (userId == userIdDel) {
			result = false;
		}

		return result;
	}

	//ユーザー削除処理
	public void userDel(int userId) {

		MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate();

		MastaEntity mastaEntity = new MastaEntity(userId);
		
		//TODO トランザクション処理
		
		//ユーザーテーブル削除（論理削除）
		useraddDAOInsUp.delUser(mastaEntity);

		//ログインテーブル削除（論理削除）
		useraddDAOInsUp.delLogin(mastaEntity);
	}
}
