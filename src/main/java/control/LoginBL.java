package control;

import java.util.ArrayList;
import java.util.List;

public class LoginBL extends DBConnection {

	// 初回ログインチェック用
	public String[] checkLoginShokai(String pLoginId) {
		
		//要素0 error→エラー画面　kari→パスワード登録更新画面 true→後続処理
		//要素1 loginテーブルのログイン名に紐づくloginテーブルのIDを取得
		String[] result = {"",""};

		//TODO IDが5桁未満だと落ちるので仮、本当はID登録文字数制限とかしたほうがいい
		if (pLoginId.length() >= 4) {
			//IDが仮IDだった場合、パスワード更新登録画面に遷移
			if (pLoginId.substring(0, 4).equals("kari")) {
				result[0] = "toPassword";
				//loginテーブルid項目の取得
				LoginDAO loginDAO = new LoginDAO();
				result[1] =  loginDAO.findLoginIdtoId(pLoginId);
				//それ以外の場合は後続処理に移行
			} else {
				result[0] = "true";
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
		resultDB = loginDAO.countLoginId(pLoginId);

		// IDが重複している場合、エラー画面に遷移
		if (Integer.parseInt(resultDB) > 0) {
			result = false;
		}

		return result;

	}

}
