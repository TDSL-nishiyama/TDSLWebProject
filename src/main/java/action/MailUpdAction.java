package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * メールアドレス更新画面のサーブレット
 */
public class MailUpdAction extends HttpServlet {

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
    
    Map<String,Object> gamenInfo = new HashMap<String,Object>();
    gamenInfo.put("selId", selId);
    gamenInfo.put("mailAddress", mailAddress);

    //mailテーブルから値を取得
    try {
      mastaBeanList = mailBL.getMailTBL(gamenInfo);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //リクエストスコープに値を保存
    request.setAttribute(Path.MAIL_SCOPE, mastaBeanList);
    //メールアドレスマスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.MASTA_MAIL_UPD_GAMEN);
    dispatcher.forward(request, response);
  }
}
