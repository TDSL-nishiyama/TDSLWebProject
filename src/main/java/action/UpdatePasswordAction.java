package action;

import java.io.IOException;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * パスワード登録更新画面のサーブレット
 */
public class UpdatePasswordAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //パスワード登録更新画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.UPDATE_PASSWORD_GAMEN);
    dispatcher.forward(request, response);
  }

}
