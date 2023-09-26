package action;

import java.io.IOException;

import constents.Const.Common;
import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.CheckLogin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;

/**
 * Servlet implementation class IndexAction
 */
public class LoginAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    @Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

//        //セッションを破棄
//        HttpSession session = request.getSession();
//        session.invalidate();
//
//        String id;
//        String password;
//
//        id = request.getParameter("id");
//        password = request.getParameter("password");
//
//        // IDが入力されていない場合、エラー画面に遷移
//        if (id == null || "".equals(id)) {
//            //エラーメッセージを格納
//            request.setAttribute("ERRMSG", ERRORMSG.ERR_1);
//            // エラー画面に遷移
//            RequestDispatcher dispatcher = request
//                    .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
//            dispatcher.forward(request, response);
//            return;
//        }
//
//        // パスワードが入力されていない場合、エラー画面に遷移
//        if (password == null || "".equals(password)) {
//            //エラーメッセージを格納
//            request.setAttribute("ERRMSG", ERRORMSG.ERR_2);
//            // エラー画面に遷移
//            RequestDispatcher dispatcher = request
//                    .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
//            dispatcher.forward(request, response);
//            return;
//        }
//
//        boolean errorFlg; // ログインIDパスワード存在チェック用 true = 存在 false = 存在しない
//
//        CheckLogin checkLogin = new CheckLogin();
//
//        errorFlg = checkLogin.checkLoginId(id);
//
//        // ログインIDが存在しない場合はエラー画面に遷移
//        if (!errorFlg) {
//            //エラーメッセージを格納
//            request.setAttribute("ERRMSG", ERRORMSG.ERR_3);
//            // エラー画面に遷移
//            RequestDispatcher dispatcher = request
//                    .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
//            dispatcher.forward(request, response);
//        }
//
//        errorFlg = checkLogin.checkLoginIdAndPassword(id, password);
//
//        // ログインIDとパスワードが一致しない場合はエラー画面に遷移
//        if (!errorFlg) {
//            //エラーメッセージを格納
//            request.setAttribute("ERRMSG", ERRORMSG.ERR_4);
//            // エラー画面に遷移
//            RequestDispatcher dispatcher = request
//                    .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
//            dispatcher.forward(request, response);
//        } else {
//            //ユーザー名と管理者権限を取得
//
//            // セッション情報を格納
//            SessionKanriBean sessionKanriBean = new SessionKanriBean(id, password);
//
//            // セッション情報の文字化け対策
//            request.setCharacterEncoding(Common.ENCODE_UTF8);
//            // セッション情報の取得
//            HttpSession httpSession = request.getSession();
//            // セッションスコープにログイン情報を保存
//            httpSession.setAttribute(Path.SESSION_SCOPE_NAME, sessionKanriBean);

            // メイン画面に遷移
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher(Path.MAIN_GAMEN);
            dispatcher.forward(request, response);
        }
    }
