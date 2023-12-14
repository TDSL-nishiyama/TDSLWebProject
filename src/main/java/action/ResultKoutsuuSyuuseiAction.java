package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.KoutsuuConst.KCommon;
import control.KoutsuuBL;
import control.common.CastCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KoutsuuBean;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class ResultKoutsuuSyuuseiAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    CastCommon castCommon = new CastCommon();
    
    //画面情報を取得
    String selId = request.getParameter("selId");
    String selNo = request.getParameter("selNo");
    String riyouhiduke = request.getParameter("riyouhiduke");
    String kukans = request.getParameter("kukans");
    String kukane = request.getParameter("kukane");
    String kingaku = request.getParameter("kingaku");
    String bikou = request.getParameter("bikou");
    
    //更新内容をMAPに格納
    Map<String,Object> gamenInfo = new LinkedHashMap<String,Object>();
    gamenInfo.put("selId", Integer.parseInt(selId));
    gamenInfo.put("selNo", Integer.parseInt(selNo));
    gamenInfo.put("riyouhiduke", castCommon.chgStrtoLDT(riyouhiduke));
    gamenInfo.put("kukans", kukans);
    gamenInfo.put("kukane", kukane);
    gamenInfo.put("kingaku", kingaku);
    gamenInfo.put("bikou", bikou);
    gamenInfo.put("modoshiriyuu",request.getParameter("modoshiriyuu").toString());
    
    //エラーチェックSTART
    //エラーチェックEND
    
    //更新処理
    try {
      koutsuuBL.updKoutsuuSyuusei(gamenInfo);
      //リクエストスコープに値を格納
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(Integer.parseInt(selId),KCommon.NONSELSTA,KCommon.QUERY_TYPE_0_SELID);
      request.setAttribute(Path.KOUTSUU_KAKUNIN_SCOPE, koutsuuBeanList);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //メッセージ格納
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.K_SYUUSEI_1);
    //交通費修正画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_SYUUSEI_GAMEN);
    dispatcher.forward(request, response);
  }
}
