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
 * メールアドレスマスタ画面のサーブレット
 */
public class MailAddAction extends HttpServlet {

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    MailBL mailBL = new MailBL();
    CheckCommon checkCommon = new CheckCommon();
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();

    //必須チェック
    boolean errFlg = false;
    errFlg = mailBL.checkHissuKoumoku(request.getParameter("selId"), request.getParameter("mailAddress"));
    //エラーフラグが立っている場合
    if (errFlg == true) {
      //画面の値を保持
      saveParameter(request);
      //メッセージを設定してメールアドレスマスタ画面に戻る
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_2);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //画面情報から値を取得
    int selId = Integer.parseInt(request.getParameter("selId"));
    String mailAddress = request.getParameter("mailAddress");
    //画面情報の値を格納
    Map<String, Object> gamenInfo = new HashMap<>();
    gamenInfo.put("selId", selId);
    gamenInfo.put("mailAddress", mailAddress);

    //ID存在チェック
    if (checkCommon.checkUserId(selId) == 0) {
      //画面の値を保持
      saveParameter(request);
      //メッセージを設定してメールアドレスマスタ画面に戻る
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_1);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //メールアドレス多重登録チェック
    if (mailBL.checkTajuuTouroku(selId, mailAddress)) {
      //画面の値を保持
      saveParameter(request);
      //メッセージを設定してメールアドレスマスタ画面に戻る
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_3);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //更新処理
    try {
      mailBL.insertMailTBL(gamenInfo);
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
      mastaBeanList = mailBL.getMailTBL();
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
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_MAIL_4);
    //メールアドレスマスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.MASTA_MAIL_HOME_GAMEN);
    dispatcher.forward(request, response);
  }
  
  private void saveParameter(HttpServletRequest request) {
    List<MastaBean> mastaBeanList = new ArrayList<MastaBean>();
    MailBL mailBL = new MailBL();
    try {
      mastaBeanList = mailBL.getMailTBL();
    } catch (SQLException e) {
      //何もしない
    }
    request.setAttribute(Path.MAIL_SCOPE, mastaBeanList);
    request.setAttribute("selId", request.getParameter("selId"));
    request.setAttribute("mailAddress", request.getParameter("mailAddress"));
  }

}
