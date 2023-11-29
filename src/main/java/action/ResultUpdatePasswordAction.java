package action;

import java.io.IOException;
import java.sql.SQLException;

import constents.Const.Common;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import control.LoginBL;
import control.ResultUpdatePasswordBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * パスワード登録更新画面のサーブレット
 */
public class ResultUpdatePasswordAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ResultUpdatePasswordBL resultUpdatePasswordBL = new ResultUpdatePasswordBL();
    final String GAMEN_LOGINID = "loginid";
    final String GAMEN_PASSWORD1 = "pass1";
    String loginId = request.getParameter(GAMEN_LOGINID);
    String password = request.getParameter(GAMEN_PASSWORD1);

    boolean errFlg;
    String resultMSG = "";

    LoginBL loginBL = new LoginBL();

    HttpSession session = request.getSession();
    //新規ユーザーの場合
    if (session.getAttribute(Path.USER_ATTRIBUTE).equals(Common.SHINKI)) {

      //ログインIDが5桁未満の場合、エラーメッセージを表示
      errFlg = loginBL.checkLoginIdLength(loginId);
      if (!errFlg) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE,  ERRORMSG.ERR_6);
        // エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }

      //ユーザー名が重複している場合はエラーメッセージを表示
      errFlg = loginBL.checkDuplicationLoginId(loginId);
      if (!errFlg) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_7);
        // エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }

      //ユーザー名の先頭4文字にkariをつけることは禁止（不正ID作成対策）
      errFlg = resultUpdatePasswordBL.checkLoginIdConditions(loginId);
      if (!errFlg) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE,  ERRORMSG.ERR_8);
        // エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }

      //ユーザーの登録
      String loginIdBefore = (String) session.getAttribute(Path.BEFORE_LOGIN);
      try {
        resultUpdatePasswordBL.updateUserPassword(loginIdBefore, loginId, password);
      } catch (SQLException e) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE,  ERRORMSG.DBERROR);
        //エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
      resultMSG = MSG.UPDPASS_1;
      //既存ユーザーの場合
    } else {
      //パスワードの更新
      
      try {
        resultUpdatePasswordBL.updatePassword(loginId, password);
      } catch (SQLException e) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE,  ERRORMSG.DBERROR);
        //エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
      resultMSG = MSG.UPDPASS_2;
    }

    //ログイン画面に表示させるメッセージを格納
    request.setAttribute(MSG.MSG_ATTRIBUTE, resultMSG);
    //セッションを破棄
    session.removeAttribute(Path.USER_ATTRIBUTE);
    session.removeAttribute(Path.BEFORE_LOGIN);
    session.invalidate();
    //ログイン画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.LOGIN_GAMEN);
    dispatcher.forward(request, response);
  }

}
