package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.MailBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaBean;

/**
 * メールアドレスマスタ画面のサーブレット
 */
public class MailMastaAction extends HttpServlet {

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    //mailテーブルから値を取得
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();
    MailBL mailBL = new MailBL();
    try {
      mastaBeanList = mailBL.getMailTBL();
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //リクエストスコープに値を保存
    request.setAttribute(Path.MAIL_SCOPE, mastaBeanList);
    
    //メールアドレスマスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
    dispatcher.forward(request, response);
  }

}
