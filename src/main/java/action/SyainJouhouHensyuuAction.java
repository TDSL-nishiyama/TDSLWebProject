package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Path;
import control.SyainJouhouBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;
import model.SyainJouhouBean;

public class SyainJouhouHensyuuAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    //管理者フラグの取得
    SessionKanriBean loginSession = 
        (SessionKanriBean)session.getAttribute(Path.SESSION_SCOPE);
    
    boolean kanriFlg = false;
    kanriFlg = loginSession.getKanriFlg();
    
    //画面情報の取得
    int updUserId = Integer.parseInt(request.getParameter("chgUserId"));
    
    //データ格納処理
    SyainJouhouBL syainJouhouBL = new SyainJouhouBL(kanriFlg);    

    //リクエストスコープにインスタンスを保存
    List<SyainJouhouBean> syainJouhouBLlist = syainJouhouBL.resultSyainJouhouHensyu(syainJouhouBL,updUserId);
    request.setAttribute(Path.SYAIN_HENSYU_SCOPE, syainJouhouBLlist);
    // 社員情報編集画面(syainJouhouBLlist.jsp)にフォワード
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
    dispatcher.forward(request, response);
  }
}