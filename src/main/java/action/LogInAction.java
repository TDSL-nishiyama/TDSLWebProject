package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Common;
import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.LoginBL;
import control.IndexDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;

/**
 * ログイン処理のサーブレット
 */
public class LogInAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//セッションを破棄
		HttpSession session = request.getSession();
		session.invalidate();

		String id;
		String password;

		id = request.getParameter("id");
		password = request.getParameter("password");

		boolean errorFlg; // ログインIDパスワード存在チェック用 true = 存在 false = 存在しない

		LoginBL loginBL = new LoginBL();
		
		// IDが入力されていない場合はエラー画面に遷移
		if (id == null || "".equals(id)) {
			//エラーメッセージを格納
			request.setAttribute("ERRMSG", ERRORMSG.ERR_1);
			// エラー画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
			dispatcher.forward(request, response);
			return;
		}

		// パスワードが入力されていない場合はエラー画面に遷移
		if (password == null || "".equals(password)) {
			//エラーメッセージを格納
			request.setAttribute("ERRMSG", ERRORMSG.ERR_2);
			// エラー画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
			dispatcher.forward(request, response);
			return;
		}
		
		//TODO 初回ログイン時はパスワード登録画面に遷移
		

		errorFlg = loginBL.checkLoginId(id);

		// ログインIDが存在しない場合はエラー画面に遷移
		if (!errorFlg) {
			//エラーメッセージを格納
			request.setAttribute("ERRMSG", ERRORMSG.ERR_3);
			// エラー画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
			dispatcher.forward(request, response);
		}

		errorFlg = loginBL.checkLoginIdAndPassword(id, password);

		// ログインIDとパスワードが一致しない場合はエラー画面に遷移
		if (!errorFlg) {
			//エラーメッセージを格納
			request.setAttribute("ERRMSG", ERRORMSG.ERR_4);
			// エラー画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
			dispatcher.forward(request, response);
		} else {
			//ユーザー名と管理者権限を取得
			IndexDAO indexDAO = new IndexDAO();
			List<String> sList = indexDAO.getSessionInfo(id, password);
			
			// セッション情報を格納
			final int  ID = 0;
			final int  PASSWORD = 1;
			final int  NAME = 2;
			final int  KANRIFLG = 3;
			SessionKanriBean sessionKanriBean = 
					new SessionKanriBean(sList.get(ID),sList.get(PASSWORD),sList.get(NAME),Boolean.valueOf(sList.get(KANRIFLG)));

			// セッション情報の文字化け対策
			request.setCharacterEncoding(Common.ENCODE_UTF8);
			// セッションスコープの作成
			HttpSession httpSession = request.getSession();
			// セッションスコープにログイン情報を保存
			httpSession.setAttribute(Path.SESSION_SCOPE_NAME, sessionKanriBean);
			// メイン画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.MAIN_GAMEN);
			dispatcher.forward(request, response);
		}
	}
}
