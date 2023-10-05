package action;

import java.io.IOException;

import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.LoginBL;
import control.ResultUpdatePasswordBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * パスワード登録更新画面のサーブレット
 */
public class ResultUpdatePasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ログインIDに紐づくユーザーIDを取得
		ResultUpdatePasswordBL resultUpdatePasswordBL = new ResultUpdatePasswordBL();
		
		String userId = resultUpdatePasswordBL.getUserId("loginid");
		String loginId = request.getParameter("loginid");
		String password = request.getParameter("pass1");
		
		boolean errFlg;
		String resultMSG = "";
		
		LoginBL loginBL = new LoginBL();
		
		//ユーザーID重複チェック
		//既存ユーザーの場合、usernameテキストボックスは表示されないため判定自体を行わない
		if (!(loginId == "" || loginId.equals(null))) {
			errFlg = loginBL.checkDuplicationLoginId(loginId);
			// ユーザー名が重複している場合はエラーメッセージを表示
			if (!errFlg) {
				//エラーメッセージを格納
				request.setAttribute("ERRMSG", ERRORMSG.ERR_6);
				// エラー画面に遷移
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
				dispatcher.forward(request, response);
			}
			//ユーザーの登録
			resultUpdatePasswordBL.updateUserPassword(userId, loginId, password);
			resultMSG = "ユーザーの登録が完了しました。再度ログインをお願いします";
		}else {
			//パスワードの更新
			resultUpdatePasswordBL.updatePassword(userId, password);
			resultMSG = "パスワードの更新が完了しました";
		}
		
		//ログイン画面に表示させるメッセージを格納
		request.setAttribute("MSG",resultMSG);

		//ログイン画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.LOGIN_GAMEN);
		dispatcher.forward(request, response);
	}

}
