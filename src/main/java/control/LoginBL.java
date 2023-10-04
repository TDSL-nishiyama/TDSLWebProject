package control;

import java.util.ArrayList;
import java.util.List;

public class LoginBL extends DBConnection {

	// 初回ログインチェック用
	public String checkLoginShokai(String pLoginId) {

		String result = "false";

		//TODO IDが5桁未満だと落ちるので仮、本当はID登録文字数制限とかしたほうがいい
		if (pLoginId.length() >= 4) {
			//IDが仮IDだった場合、パスワード更新登録画面に遷移
			if (pLoginId.substring(0, 4).equals("kari")) {
				result = "toPassword";
				//それ以外の場合は後続処理に移行
			} else {
				result = "true";
			}
		}

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

	// ログインID重複チェック用
	public boolean checkDuplicationLoginId(String pLoginId) {

		boolean result = true;
		String resultDB = null;

		LoginDAO loginDAO = new LoginDAO();
		resultDB = loginDAO.findUserId(pLoginId);

		// IDが重複している場合、エラー画面に遷移
		if (Integer.parseInt(resultDB) > 0) {
			result = false;
		}

		return result;

	}

}
