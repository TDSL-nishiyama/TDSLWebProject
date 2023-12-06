package action;

import java.io.IOException;
import java.util.List;
import constents.Const.MSG;
import constents.Const.Path;
import control.SyainJouhouBL;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;
import model.SyainJouhouBean;
import model.SyainJouhouHensyuuBean;

public class SyainJouhouHensyuuAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    //管理者フラグの取得
    SessionKanriBean loginSession = (SessionKanriBean) session.getAttribute(Path.SESSION_SCOPE);

    boolean kanriFlg = false;
    kanriFlg = loginSession.getKanriFlg();

    //画面情報の取得
    int updUserId = Integer.parseInt(request.getParameter("chgUserId"));

    //一般ユーザーは自分の情報以外は更新することができない
    CheckCommon checkCommon = new CheckCommon();
    if (kanriFlg == false) {
      //ログインしているユーザーのIDと選択したIDが異なる場合エラー
      if (checkCommon.checkLoginIdEqualsId(loginSession.getUserId(), updUserId) == false) {
        //メッセージを格納
        StringBuilder sb = new StringBuilder();
        sb.append(MSG.SYAIN1);
        request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
        //データ格納処理
        SyainJouhouBL syainJouhouBL = new SyainJouhouBL(kanriFlg);

        //リクエストスコープにインスタンスを保存
        List<SyainJouhouBean> SyainJouhouBLlist = syainJouhouBL.resultSyainJouhou();
        request.setAttribute(Path.SYAIN_JOUHOU_SCOPE, SyainJouhouBLlist);
        // 結果出力画面(syainJouhou.jsp)にフォワード
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYAIN_JOUHOU_PATH);
        dispatcher.forward(request, response);
        return;
      }
    }

    //データ格納処理
    SyainJouhouBL syainJouhouBL = new SyainJouhouBL(kanriFlg);

    //リクエストスコープにインスタンスを保存
    List<SyainJouhouHensyuuBean> syainJouhouBLlist = syainJouhouBL.resultSyainJouhouHensyu(updUserId);
    request.setAttribute(Path.SYAIN_HENSYU_SCOPE, syainJouhouBLlist);
    // 社員情報編集画面(syainJouhouBLlist.jsp)にフォワード
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
    dispatcher.forward(request, response);
  }
}