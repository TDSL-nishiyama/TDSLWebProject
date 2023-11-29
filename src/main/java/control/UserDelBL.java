package control;

import java.sql.SQLException;

import control.common.CheckCommon;
import control.common.DAOCommon;
import model.MastaEntity;

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

		MastaEntity mastaEntity = new MastaEntity(userId);
		
		DAOCommon dao = new DAOCommon();
		try {
		  //トランザクション開始
		  dao.startTransaction();
		  //ユーザーテーブル削除（論理削除）
	    useraddDAOInsUp.delUser(mastaEntity);
	    //ログインテーブル削除（論理削除）
	    useraddDAOInsUp.delLogin(mastaEntity);
		}catch(SQLException e) {
		  dao.endTransactionFalse();
		  throw new SQLException();
		}
		dao.endTransactionTrue();
	}
}
