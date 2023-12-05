package control;

import java.sql.SQLException;

public class UserDelBL {

	/**
	 * {@index ユーザー削除処理}
	 * @param userId　削除するユーザーID
	 * @throws SQLException
	 */
	public void userDel(int userId) throws SQLException {

		MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate();
		//user・loginテーブル削除（論理削除）
		useraddDAOInsUp.delUserAndLogin(userId);

	}
}
