package action;

import java.io.IOException;
import java.sql.SQLException;
import constents.Const.Path;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import control.UserDelBL;
import control.common.CheckCommon;
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

    //共通クラスのインスタンス化
    CheckCommon checkCommon = new CheckCommon();

    boolean errflg = false;
    UserDelBL userDelBL = new UserDelBL();

    //入力されたIDが存在しない場合、メッセージを表示
    if (checkCommon.checkUserId(userIdDel) == 0) {
      //メッセージを格納
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_DEL_1);
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //現在ログインしているユーザーを削除しようとした場合、メッセージを表示    
    errflg = checkCommon.checkLoginIdEqualsId(userId, userIdDel);
    if (errflg == true) {
      //メッセージを格納
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_DEL_2);
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_DEL_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //ユーザー削除を実行
    try {
      userDelBL.userDel(userIdDel);
    } catch (SQLException e) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      //エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    //TODO ユーザー詳細TBLの退社年月日に退社日を挿入

    //登録完了メッセージ
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_DEL_3);
    //ユーザー削除画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_DEL_GAMEN);
    dispatcher.forward(request, response);

  }

}