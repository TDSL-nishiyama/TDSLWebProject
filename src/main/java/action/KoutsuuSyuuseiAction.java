package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import constents.Const.Path;
import constents.KoutsuuConst.KCommon;
import constents.KoutsuuConst.SyuuseiG;
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
public class KoutsuuSyuuseiAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    //画面情報を取得
    String selNo = request.getParameter(SyuuseiG.GAMEN_UNINO);
    
    //リクエストスコープに値を格納
    KoutsuuBL koutsuuBL = new KoutsuuBL();
    List<KoutsuuBean> koutsuuBeanList = null;
    try {
      koutsuuBeanList = koutsuuBL.getKoutsuuKakunin(KCommon.NONSELID, Integer.parseInt(selNo), KCommon.NONSELSTA,KCommon.QUERY_TYPE_4_SELNO);
    } catch (NumberFormatException | SQLException e) {
      e.printStackTrace();
    }
    request.setAttribute(Path.KOUTSUU_KAKUNIN_SCOPE, koutsuuBeanList);
    
    //交通費修正画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_SYUUSEI_GAMEN);
    dispatcher.forward(request, response);
  }
}
