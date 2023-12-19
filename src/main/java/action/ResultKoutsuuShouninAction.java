package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constents.KoutsuuConst.KCommon;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import control.KoutsuuBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KoutsuuBean;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class ResultKoutsuuShouninAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;

    //画面情報の取得
    int no = Integer.parseInt(request.getParameter("selNo"));
    String beforeStatus = request.getParameter("befStatus");
    String afterStatus = request.getParameter("status");

    //エラーチェックSTART
    //ステータスチェック処理
    Map<String, String> staCheck = new HashMap<String, String>();
    staCheck = koutsuuBL.checkStatus(beforeStatus, afterStatus);

    if (Boolean.valueOf(staCheck.get("errorFlg")) == true) {
      //エラーメッセージを表示
      try {
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.NONSELSTA,
            KCommon.QUERY_TYPE_1_ALL);
      } catch (SQLException e) {
        //何もしない
      }
      request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);
      request.setAttribute(MSG.MSG_ATTRIBUTE, staCheck.get("errorMSG"));
      //交通費精算承認画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTSUU_SHOUNIN_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    //エラーチェックEND

    //差戻の場合交通費差戻画面に遷移
    if (afterStatus.equals(KCommon.SASHIMODOSHI)) {
      try {
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, no, KCommon.NONSELSTA,KCommon.QUERY_TYPE_4_SELNO);
      } catch (SQLException e) {
        //何もしない
      }
      request.setAttribute(Path.KOUTSUU_KAKUNIN_SCOPE, koutsuuBeanList);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTSUU_SASHIMODOSHI_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //更新処理
    try {
      koutsuuBL.updKoutsuuShounin(no, afterStatus);
    } catch (SQLException e) {
      //エラーメッセージを表示
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      //システムエラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //リクエストスコープにインスタンスを保存
    try {
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.NONSELSTA,
          KCommon.QUERY_TYPE_1_ALL);
    } catch (SQLException e) {
      //エラーメッセージを表示
      request.setAttribute(MSG.MSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      //交通費精算承認画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTSUU_SHOUNIN_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);
    //メッセージを表示
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.K_SHONIN_3);
    //交通費精算承認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_SHOUNIN_GAMEN);
    dispatcher.forward(request, response);
  }

}
