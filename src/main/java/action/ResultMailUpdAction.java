package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import control.MailBL;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaBean;

/**
 * メールアドレス更新処理画面のサーブレット
 */
public class ResultMailUpdAction extends HttpServlet {

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    MailBL mailBL = new MailBL();
    CheckCommon checkCommon = new CheckCommon();
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();

    //画面情報から値を取得
    int selId = Integer.parseInt(request.getParameter("selId"));
    String beforeMailAddress = request.getParameter("beforeMailAddress");
    String afterMailAddress = request.getParameter("afterMailAddress");
    //画面情報の値を格納
    Map<String, Object> gamenInfo = new HashMap<>();
    gamenInfo.put("selId", selId);
    gamenInfo.put("beforeMailAddress", beforeMailAddress);
    gamenInfo.put("afterMailAddress", afterMailAddress);
    gamenInfo.put("mailAddress", afterMailAddress);

    //ID存在チェック
    if (checkCommon.checkUserId(selId) == 0) {
      //画面の値を保持
      saveParameter(request,gamenInfo);
      //メッセージを設定してメールアドレス更新画面に戻る
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_1);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MASTA_MAIL_UPD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //メールアドレス多重登録チェック
    if (mailBL.checkTajuuTouroku(selId, afterMailAddress)) {
      //画面の値を保持
      saveParameter(request,gamenInfo);
      //メッセージを設定してメールアドレス更新画面に戻る
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_3);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MASTA_MAIL_UPD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //更新処理
    try {
      mailBL.updateMailTBL(gamenInfo);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

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
    //メッセージを設定
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_5);
    //メールアドレス更新画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.MASTA_MAIL_UPD_GAMEN);
    dispatcher.forward(request, response);
  }
  
  private void saveParameter(HttpServletRequest request,Map<String,Object> gamenInfo) {
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();
    MailBL mailBL = new MailBL();
    try {
      mastaBeanList = mailBL.getMailTBL(gamenInfo);
    } catch (SQLException e) {
      //何もしない
    }
    request.setAttribute(Path.MAIL_SCOPE, mastaBeanList);
    request.setAttribute("selId", request.getParameter("selId"));
    request.setAttribute("afterMailAddress", request.getParameter("afterMailAddress"));
  }

}
