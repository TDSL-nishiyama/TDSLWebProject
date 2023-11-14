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
 * ユーザー削除画面のサーブレット
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
    SessionKanriBean loginSession = (SessionKanriBean) session.getAttribute(Path.SESSION_SCOPE);

    int userId = loginSession.getUserId();
    //画面に入力されたユーザーID
    int userIdDel = Integer.parseInt(request.getParameter("userIdDel"));
    
    boolean errflg = false;
    UserDelBL userDelBL = new UserDelBL();
    //入力されたIDが存在しない場合エラーメッセージを表示
    errflg = userDelBL.userDelCheck(userIdDel);
    if (errflg == false) {
      //メッセージを格納
      request.setAttribute("MSG", "入力されたユーザーIDは存在しません");
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //現在ログインしているユーザーを削除しようとした場合、エラーメッセージを表示    
    errflg = userDelBL.userDelCheck(userId, userIdDel);
    if (errflg == false) {
      //メッセージを格納
      request.setAttribute("MSG", "現在ログインしているユーザーの削除はできません");
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    
    } else {
      //ユーザー削除
      userDelBL.userDel(userIdDel);

      //登録完了メッセージ
      request.setAttribute("MSG", "ユーザーの削除が完了しました");
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
    }
  }

}