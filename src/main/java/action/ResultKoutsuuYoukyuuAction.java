package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.KoutsuuConst.YoukyuuG;
import control.KoutsuuBL;
import control.common.CastCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.KoutsuuBean;
import model.SessionKanriBean;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class ResultKoutsuuYoukyuuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //セッションからログインしているユーザーのIDを取得
    int selId = 0;
    HttpSession session = request.getSession();
    SessionKanriBean loginSession = (SessionKanriBean) session.getAttribute(Path.SESSION_SCOPE);
    selId = loginSession.getUserId();
    
    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    CastCommon castC = new CastCommon();
    
    //画面情報を取得
    String riyouhiduke1 = request.getParameter(YoukyuuG.GAMEN_RIYOUHIDUKE_1);
    String kukans1 = request.getParameter(YoukyuuG.GAMEN_KUKAN_START_1);
    String kukane1 = request.getParameter(YoukyuuG.GAMEN_KUKAN_END_1);
    String kingaku1 = request.getParameter(YoukyuuG.GAMEN_KINGAKU_1);
    String bikou1 = request.getParameter(YoukyuuG.GAMEN_BIKOU_1);
    String riyouhiduke2 = request.getParameter(YoukyuuG.GAMEN_RIYOUHIDUKE_2);
    String kukans2 = request.getParameter(YoukyuuG.GAMEN_KUKAN_START_2);
    String kukane2 = request.getParameter(YoukyuuG.GAMEN_KUKAN_END_2);
    String kingaku2 = request.getParameter(YoukyuuG.GAMEN_KINGAKU_2);
    String bikou2 = request.getParameter(YoukyuuG.GAMEN_BIKOU_2);
    
    //～１と～２をそれぞれKoutsuuBeanに格納
    KoutsuuBean bean1 = new KoutsuuBean(selId,castC.chgStrtoLDT(riyouhiduke1),kukans1,kukane1,kingaku1,bikou1);
    KoutsuuBean bean2 = new KoutsuuBean(selId,castC.chgStrtoLDT(riyouhiduke2),kukans2,kukane2,kingaku2,bikou2);
    
    //エラーチェックSTART
    
    
    //エラーチェックEND
    
    
    //更新処理
    List<KoutsuuBean> list = new ArrayList<KoutsuuBean>();
    list.add(bean1);
    if(!(riyouhiduke2.equals(""))) {
      list.add(bean2);
    }

    try {
      koutsuuBL.addKoutsuuYoukyu(list);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //メッセージ格納
    StringBuilder sb = new StringBuilder();
    sb.append(MSG.K_YOUKYU_1);
    request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
    //交通費精算要求画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTUSUU_YOUKYUU_GAMEN);
    dispatcher.forward(request, response);
	}

}
