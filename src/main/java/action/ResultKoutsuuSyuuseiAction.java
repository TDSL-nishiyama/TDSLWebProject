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
import constents.KoutsuuConst.SyuuseiG;
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
    String selId = request.getParameter(SyuuseiG.GAMEN_USERID);
    String selNo = request.getParameter(SyuuseiG.GAMEN_UNINO);
    String riyouhiduke = request.getParameter(SyuuseiG.GAMEN_RIYOUHIDUKE);
    String kukans = request.getParameter(SyuuseiG.GAMEN_KUKAN_S);
    String kukane = request.getParameter(SyuuseiG.GAMEN_KUKAN_E);
    String kingaku = request.getParameter(SyuuseiG.GAMEN_KINGAKU);
    String bikou = request.getParameter(SyuuseiG.GAMEN_BIKOU);
    String modoshiriyuu = request.getParameter(SyuuseiG.GAMEN_MODOSHIRIYUU);
    
    //更新内容をMAPに格納
    Map<String,Object> gamenInfo = new LinkedHashMap<String,Object>();
    gamenInfo.put(SyuuseiG.GAMEN_USERID, Integer.parseInt(selId));
    gamenInfo.put(SyuuseiG.GAMEN_UNINO, Integer.parseInt(selNo));
    gamenInfo.put(SyuuseiG.GAMEN_RIYOUHIDUKE, castCommon.chgStrtoLDT(riyouhiduke));
    gamenInfo.put(SyuuseiG.GAMEN_KUKAN_S, kukans);
    gamenInfo.put(SyuuseiG.GAMEN_KUKAN_E, kukane);
    gamenInfo.put(SyuuseiG.GAMEN_KINGAKU, kingaku);
    gamenInfo.put(SyuuseiG.GAMEN_BIKOU, bikou);
    gamenInfo.put(SyuuseiG.GAMEN_MODOSHIRIYUU,modoshiriyuu);
    
    //エラーチェックSTART
    //エラーチェックEND
    
    //更新処理
    try {
      koutsuuBL.updKoutsuuSyuusei(gamenInfo);
      //リクエストスコープに値を格納
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID,KCommon.NONSELNO,KCommon.NONSELSTA,KCommon.QUERY_TYPE_1_ALL);
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
    //交通費確認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_KAKUNIN_GAMEN);
    dispatcher.forward(request, response);
  }
}
