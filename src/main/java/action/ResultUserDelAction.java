package action;

import java.io.IOException;

import constents.Const.Path;
import control.UserDelBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;

/**
 * ユーザー登録画面のサーブレット
 */
public class ResultUserDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//現在ログインしているユーザーID
		HttpSession session = request.getSession();
		SessionKanriBean loginSession = (SessionKanriBean) session.getAttribute(Path.SESSION_SCOPE_NAME);

		int userId = loginSession.getUserId();
		//画面に入力されたユーザーID
		int userIdDel = Integer.parseInt(request.getParameter("userIdDel"));

		//現在ログインしているユーザーを削除しようとした場合、エラーメッセージを表示
		boolean errflg = false;
		UserDelBL userDelBL = new UserDelBL();
		errflg = userDelBL.userDelCheck(userId, userIdDel);
		if (errflg == false) {
			//メッセージを格納
			request.setAttribute("MSG", "現在ログインしているユーザーの削除はできません");
			//マスタ画面に遷移
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.USER_DEL_GAMEN);
			dispatcher.forward(request, response);
		}
		//ユーザー削除
		userDelBL.userDel(userId);

		//登録完了メッセージ
		request.setAttribute("MSG", "ユーザーの削除が完了しました");
		//マスタ画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.USER_DEL_GAMEN);
		dispatcher.forward(request, response);
	}

}