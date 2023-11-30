package control;

import java.sql.SQLException;

import control.common.CheckCommon;

public class UserDelBL {

	//ID存在確認
	public boolean userDelCheck(int userIdDel) {

		boolean result = true;

		CheckCommon checkCommon = new CheckCommon();

		//ユーザーID確認
		int i = checkCommon.checkUserId(userIdDel);

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
	public void userDel(int userId) throws SQLException {

		MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate();
		//user・loginテーブル削除（論理削除）
		useraddDAOInsUp.delUserAndLogin(userId);

	}
}
