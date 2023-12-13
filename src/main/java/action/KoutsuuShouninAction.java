package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
public class KoutsuuShouninAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //リクエストスコープにインスタンスを保存
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    //呼び出しクエリの選択
    String queryType = ""; //switchにNULL渡すとコケる
    if (request.getParameter("type") != null) {
      queryType = request.getParameter("type");
    }
    //id指定があった場合、IDを格納
    int id = 0;
    if (request.getParameter("selId") != null) {
      id = Integer.parseInt(request.getParameter("selId"));
    }
    
    //エラーチェックSTART
    //ID入力チェック
    if(request.getParameter("selId") != null && request.getParameter("selId").equals("")){
      //エラーメッセージの設定
      request.setAttribute(MSG.MSG_ATTRIBUTE, "IDを選択してください");
      //交通費精算承認画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTUSUU_SHOUNIN_GAMEN);
      dispatcher.forward(request, response);
    }
    //エラーチェックEND

    try {
      //QUERY_TYPE属性によってDAOのパラメータを変更
      switch (queryType) {
      case "0":
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(id, KCommon.NONSELSTA, KCommon.QUERY_TYPE_0_SELID);
        break;
      case "1":
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELSTA, KCommon.QUERY_TYPE_1_ALL);
        break;
      case "2":
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, request.getParameter("status"), KCommon.QUERY_TYPE_2_SELSTA);
        break;
      case "3":
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.SHINSEI, KCommon.QUERY_TYPE_3_JOGAI);
        break;
      default:
        koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELSTA, KCommon.QUERY_TYPE_1_ALL);
      }
    } catch (SQLException e) {
      //エラーメッセージを表示
      request.setAttribute(MSG.MSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);

    //交通費精算承認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTUSUU_SHOUNIN_GAMEN);
    dispatcher.forward(request, response);
  }

}
