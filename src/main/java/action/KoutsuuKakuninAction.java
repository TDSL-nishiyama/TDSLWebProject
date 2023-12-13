package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import constents.Const.ERRORMSG;
import constents.Const.Path;
import constents.KoutsuuConst.KCommon;
import control.KoutsuuBL;
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
public class KoutsuuKakuninAction extends HttpServlet {
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
    
    //リクエストスコープにインスタンスを保存
	  KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    try {
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(selId,KCommon.NONSELSTA,KCommon.QUERY_TYPE_0_SELID);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_KAKUNIN_SCOPE, koutsuuBeanList);
	  
    //交通費精算確認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTUSUU_KAKUNIN_GAMEN);
    dispatcher.forward(request, response);
	}

}
