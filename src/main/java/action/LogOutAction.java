package action;

import java.io.IOException;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOutAction
 */
public class LogOutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see ログアウト処理のサーブレット
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションを破棄
		HttpSession session = request.getSession();
		session.removeAttribute(Path.SESSION_SCOPE_NAME);
		session.invalidate();

		//ログイン画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher(Path.LOGIN_GAMEN);
		dispatcher.forward(request, response);

	}

}
