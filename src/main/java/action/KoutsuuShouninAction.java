package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import constents.KoutsuuConst.KCommon;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import control.KoutsuuBL;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KoutsuuBean;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class KoutsuuShouninAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    CheckCommon checkCommon = new CheckCommon();

    //エラーチェックSTART
    String errMSG = null;
    String id = request.getParameter("selId");
    //ID入力チェック
    if (id != null) {
      if (id.isBlank()) {
        errMSG = MSG.K_SHONIN_1;
      } else if (checkCommon.checkUserId(Integer.parseInt(id)) == 0) {
        errMSG = MSG.K_SHONIN_2;
      }
      //リクエストスコープに値を保存
      try {
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.NONSELSTA,
            KCommon.QUERY_TYPE_1_ALL);
        //テーブル検索の際のエラーの場合
      } catch (SQLException e) {
        //エラーメッセージを表示
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
        //システムエラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
      request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);
      //エラーメッセージの設定
      request.setAttribute(MSG.MSG_ATTRIBUTE, errMSG);
      //交通費精算承認画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTSUU_SHOUNIN_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    //エラーチェックEND

    //呼び出しクエリの選択
    String queryType = ""; //switchにNULL渡すとコケる
    if (request.getParameter("type") != null) {
      queryType = request.getParameter("type");
    }

    //リクエストスコープに値を保存
    try {
      //QUERY_TYPE属性によってDAOのパラメータを変更
      switch (queryType) {
      case KCommon.QUERY_TYPE_0_SELID:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(Integer.parseInt(id), KCommon.NONSELNO, KCommon.NONSELSTA,
            KCommon.QUERY_TYPE_0_SELID);
        break;
      case KCommon.QUERY_TYPE_1_ALL:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.NONSELSTA,
            KCommon.QUERY_TYPE_1_ALL);
        break;
      case KCommon.QUERY_TYPE_2_SELSTA:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO,
            request.getParameter("status"),
            KCommon.QUERY_TYPE_2_SELSTA);
        break;
      case KCommon.QUERY_TYPE_3_JOGAI:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.FURIKOMIZUMI,
            KCommon.QUERY_TYPE_3_JOGAI);
        break;
      default:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELNO, KCommon.NONSELSTA,
            KCommon.QUERY_TYPE_1_ALL);
      }
    } catch (SQLException e) {
      //エラーメッセージを表示
      request.setAttribute(MSG.MSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      //システムエラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);

    //交通費精算承認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_SHOUNIN_GAMEN);
    dispatcher.forward(request, response);
  }

}
