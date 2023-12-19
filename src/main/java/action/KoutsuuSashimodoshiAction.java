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
import model.KoutsuuBean;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class KoutsuuSashimodoshiAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    //共通クラスのインスタンス化
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    
    int selNo = Integer.parseInt(request.getParameter("selNo"));
    
    try {
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID,selNo,KCommon.NONSELSTA,KCommon.QUERY_TYPE_4_SELNO);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.KOUTSUU_KAKUNIN_SCOPE, koutsuuBeanList);
    

    //交通費差戻要求画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_SASHIMODOSHI_GAMEN);
    dispatcher.forward(request, response);
	}

}
