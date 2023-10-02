package control;

import java.util.ArrayList;
import java.util.List;

public class LoginBL extends DBConnection {
	
	// ログインID存在チェック用
	public boolean checkLoginId(String pLoginId) {

		boolean result = false;

		IndexDAO indexDAO = new IndexDAO();

		// IDがDB内に存在しなかった場合エラー画面に遷移
		if (!(indexDAO.findLoginId(pLoginId) == null)) {
			result = true;
		}

		return result;

	}

	// ログインパスワードとIDの紐づきチェック用
	public boolean checkLoginIdAndPassword(String pLoginId,
			String pLoginPassword) {

		boolean result = false;

		IndexDAO indexDAO = new IndexDAO();

		// 入力したパスワードがDB内のパスワードと異なる場合エラー画面に遷移
		List<String> loginIdPassList = new ArrayList<>();
		loginIdPassList.add(pLoginId);
		loginIdPassList.add(pLoginPassword);

		if (indexDAO.findLoginIdAndPassword(pLoginId, pLoginPassword).equals(
				loginIdPassList)) {

			result = true;
		}	

		return result;
	}

}
