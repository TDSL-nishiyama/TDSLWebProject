package action;

import java.io.IOException;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ログアウト処理のサーブレット
 */
public class LogOutAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see 
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
