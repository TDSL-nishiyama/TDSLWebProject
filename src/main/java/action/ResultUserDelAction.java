package action;

import java.io.IOException;

import constents.Const.Path;
import constents.Const.MSG;
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
    //入力されたIDが存在しない場合、メッセージを表示
    errflg = userDelBL.userDelCheck(userIdDel);
    if (errflg == false) {
      //メッセージを格納
      request.setAttribute(MSG.MSG_ATTRIBUTE,MSG.MASTA_DEL_1 );
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //現在ログインしているユーザーを削除しようとした場合、メッセージを表示    
    errflg = userDelBL.userDelCheck(userId, userIdDel);
    if (errflg == false) {
      //メッセージを格納
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_DEL_2);
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    
    } else {
      //ユーザー削除を実行
      userDelBL.userDel(userIdDel);
      //TODO ユーザー詳細TBLの退社年月日に退社日を挿入

      //登録完了メッセージ
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_DEL_3);
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
    }
  }

}