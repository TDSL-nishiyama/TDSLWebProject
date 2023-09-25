package action;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import constents.Const.Path;

/**
 * Servlet implementation class LoginOutAction
 */
public class LoginOutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOutAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
