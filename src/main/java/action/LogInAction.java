package action;

import java.io.IOException;
import java.util.List;
import constents.Const.Common;
import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.LoginBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;

/**
 * ログイン処理のサーブレット
 */
public class LogInAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    //セッションを破棄
    HttpSession session = request.getSession();
    session.invalidate();

    String loginId; //ログインID
    String loginPassword; //ログインパスワード
    final String GAMEN_LOGINID = "id";
    final String GAMEN_LOGINPASSWORD = "password";

    loginId = request.getParameter(GAMEN_LOGINID);
    loginPassword = request.getParameter(GAMEN_LOGINPASSWORD);

    boolean errorFlg; // ログインIDパスワード存在チェック用 true = 存在 false = 存在しない

    LoginBL loginBL = new LoginBL();

    // IDが入力されていない場合はエラー画面に遷移
    if (loginId == null || "".equals(loginId)) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_1);
      // エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    // パスワードが入力されていない場合はエラー画面に遷移
    if (loginPassword == null || "".equals(loginPassword)) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_2);
      // エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    // ログインIDが存在しない場合はエラー画面に遷移
    errorFlg = loginBL.checkLoginId(loginId);

    if (!errorFlg) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_3);
      // エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    // ログイン初回判定用
    //要素0 error→エラー画面　kari→パスワード登録更新画面 true→後続処理
    //要素1 loginテーブルのログイン名に紐づくloginテーブルのIDを取得
    String loginIdCheck[] = { "", "" };

    // 初回ログイン時はパスワード登録画面に遷移
    loginIdCheck = loginBL.checkLoginShokai(loginId);

    if (loginId == null || loginId.equals("") || loginIdCheck[0].equals("error")) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_3);
      // エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    } else if (loginIdCheck[0].equals("toPassword")) {
      // リクエストスコープにLoginActionからの遷移である情報を追加
      request.setAttribute(Path.BEFORE_ACTION, "LoginAction");
      request.setAttribute(Path.BEFORE_LOGIN, loginId);
      // パスワード登録更新画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.UPDATE_PASSWORD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    // ログインIDとパスワードが一致しない場合はエラー画面に遷移
    errorFlg = loginBL.checkLoginIdAndPassword(loginId, loginPassword);

    if (!errorFlg) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.ERR_4);
      // エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    } else {
      //セッション情報を取得
      List<Object> sessionInfo = loginBL.getSessionInfo(loginId);

      // セッション情報を格納
      final int USERID = 0;
      final int LOGINID = 1;
      final int NAME = 2;
      final int KANRIFLG = 3;
      
      SessionKanriBean sessionKanriBean = new SessionKanriBean();
      
      sessionKanriBean.setUserId((int) sessionInfo.get(USERID));
      sessionKanriBean.setLoginId((String) sessionInfo.get(LOGINID));
      sessionKanriBean.setLoginName((String) sessionInfo.get(NAME));
      sessionKanriBean.setKanriFlg((boolean) sessionInfo.get(KANRIFLG));

      // セッション情報の文字化け対策
      request.setCharacterEncoding(Common.ENCODE_UTF8);
      // セッションスコープの作成
      HttpSession httpSession = request.getSession();
      // セッションスコープにログイン情報を保存
      httpSession.setAttribute(Path.SESSION_SCOPE, sessionKanriBean);
      // メイン画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MAIN_GAMEN);
      dispatcher.forward(request, response);
    }
  }
}
