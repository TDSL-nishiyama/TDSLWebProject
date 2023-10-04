package action;

import java.io.IOException;

import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.LoginBL;
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

		//ユーザーID重複チェック
		LoginBL loginBL = new LoginBL();
		String username = request.getParameter("username");
		boolean errFlg;
		
		//既存ユーザーの場合、usernameテキストボックスは表示されないため判定自体を行わない
		if (!(username == "" || username.equals(null))) {
			errFlg = loginBL.checkDuplicationLoginId(username);
			// ユーザー名が重複している場合はエラーメッセージを表示
			if (!errFlg) {
				//エラーメッセージを格納
				request.setAttribute("ERRMSG", ERRORMSG.ERR_6);
				// エラー画面に遷移
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
				dispatcher.forward(request, response);
			}
		}

		//ログイン画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.LOGIN_GAMEN);
		dispatcher.forward(request, response);
	}

}
