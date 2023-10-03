package control;

import java.util.ArrayList;
import java.util.List;

public class LoginBL extends DBConnection {
	
	// 初回ログインチェック用
	public boolean checkLoginShokai(String pLoginId) {
		
		boolean result = false;
		
		
		return result;
		
	}
	
	// ログインID存在チェック用
	public boolean checkLoginId(String pLoginId) {

		boolean result = false;

		LoginDAO loginDAO = new LoginDAO();

		// IDがDB内に存在しなかった場合エラー画面に遷移
		if (!(loginDAO.findLoginId(pLoginId) == null)) {
			result = true;
		}

		return result;

	}

	// ログインパスワードとIDの紐づきチェック用
	public boolean checkLoginIdAndPassword(String pLoginId,
			String pLoginPassword) {

		boolean result = false;

		LoginDAO loginDAO = new LoginDAO();

		// 入力したパスワードがDB内のパスワードと異なる場合エラー画面に遷移
		List<String> loginIdPassList = new ArrayList<>();
		loginIdPassList.add(pLoginId);
		loginIdPassList.add(pLoginPassword);

		if (loginDAO.findLoginIdAndPassword(pLoginId, pLoginPassword).equals(
				loginIdPassList)) {

			result = true;
		}	

		return result;
	}

}
