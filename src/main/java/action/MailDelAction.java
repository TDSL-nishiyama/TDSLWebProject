package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import control.MailBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaBean;

/**
 * メールアドレス更新画面のサーブレット
 */
public class MailDelAction extends HttpServlet {

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    MailBL mailBL = new MailBL();
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();

    //画面情報から値を取得
    int selId = Integer.parseInt(request.getParameter("selId"));
    String mailAddress = request.getParameter("mailAddress");
    
    //削除処理
    try {
      mailBL.deleteMailTBL(selId,mailAddress);
      mastaBeanList = mailBL.getMailTBL();
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    //メッセージを格納
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_6);
    //リクエストスコープに値を保存
    request.setAttribute(Path.MAIL_SCOPE, mastaBeanList);
    //メールアドレスマスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
    dispatcher.forward(request, response);
  }
}
