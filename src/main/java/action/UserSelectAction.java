package action;

import java.io.IOException;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ユーザー登録画面のサーブレット
 */
public class UserSelectAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    //リクエストスコープにインスタンスを保存
    
    //ユーザー一覧画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ICHIRAN_GAMEN);
    dispatcher.forward(request, response);
  }

}
