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
public class ResultKoutsuuShouninAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    
    //エラーチェックSTART
    
    //エラーチェックEND
    
    //差戻の場合差戻画面に遷移
    
    
    //更新処理
    try {
      koutsuuBL.updKoutsuuShounin(Integer.parseInt(request.getParameter("selNo")),request.getParameter("status"));
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
    List<KoutsuuBean> koutsuuBeanList =null;
    try {
       koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, KCommon.NONSELSTA, KCommon.QUERY_TYPE_1_ALL);
    } catch (SQLException e) {
      //エラーメッセージを表示
      request.setAttribute(MSG.MSG_ATTRIBUTE, ERRORMSG.DBERROR_SEL);
      //交通費精算承認画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.KOUTUSUU_SHOUNIN_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_SHOUNIN_SCOPE, koutsuuBeanList);
    //メッセージを表示
    request.setAttribute(MSG.MSG_ATTRIBUTE, "更新が完了しました");
    //交通費精算承認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTUSUU_SHOUNIN_GAMEN);
    dispatcher.forward(request, response);
  }

}
