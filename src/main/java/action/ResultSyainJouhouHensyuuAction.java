package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.UserShousai;
import control.SyainJouhouBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;
import model.SyainJouhouBean;

/**
 * Servlet implementation class ResultSyainJouhouHensyuuAction
 */
public class ResultSyainJouhouHensyuuAction extends jakarta.servlet.http.HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    //管理者フラグの取得
    SessionKanriBean loginSession = (SessionKanriBean) session.getAttribute(Path.SESSION_SCOPE);

    boolean kanriFlg = false;
    kanriFlg = loginSession.getKanriFlg();

    //画面情報の取得
    int updUserId = Integer.parseInt(request.getParameter("updUserId"));
    String sei = request.getParameter(UserShousai.GAMEN_SEI);
    String mei = request.getParameter(UserShousai.GAMEN_MEI);
    String seiyomi = request.getParameter(UserShousai.GAMEN_SEIYOMI);
    String meiyomi = request.getParameter(UserShousai.GAMEN_MEIYOMI);
    String seibetsu = request.getParameter(UserShousai.GAMEN_SEIBETSU);
    String seinenngappi = request.getParameter(UserShousai.GAMEN_SEINENNGAPPI);
    String nyuusyaYMD = request.getParameter(UserShousai.GAMEN_NYUUSYAYMD);
    String syusshin = request.getParameter(UserShousai.GAMEN_SYUSSSHIN);
    String juusyo = request.getParameter(UserShousai.GAMEN_JUUSYO);

    //更新情報の格納
    Map<String, Object> updKoumoku = new HashMap<String, Object>();
    updKoumoku.put(UserShousai.GAMEN_SEI, sei);
    updKoumoku.put(UserShousai.GAMEN_MEI, mei);
    updKoumoku.put(UserShousai.GAMEN_SEIYOMI, seiyomi);
    updKoumoku.put(UserShousai.GAMEN_MEIYOMI, meiyomi);
    updKoumoku.put(UserShousai.GAMEN_SEIBETSU, seibetsu);
    updKoumoku.put(UserShousai.GAMEN_SEINENNGAPPI, seinenngappi);
    updKoumoku.put(UserShousai.GAMEN_NYUUSYAYMD, nyuusyaYMD);
    updKoumoku.put(UserShousai.GAMEN_SYUSSSHIN, syusshin);
    updKoumoku.put(UserShousai.GAMEN_JUUSYO, juusyo);

    //TODO 差分確認処理

    //データ更新処理
    SyainJouhouBL syainJouhouBL = new SyainJouhouBL(kanriFlg);
    try {
      syainJouhouBL.syainJouhouUpd(updKoumoku, updUserId);
    } catch (SQLException e) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE,  ERRORMSG.DBERROR);
      //エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //ユーザー情報を取得
    List<SyainJouhouBean> syainJouhouBLlist = syainJouhouBL.resultSyainJouhouHensyu(syainJouhouBL, updUserId);

    //更新完了メッセージを設定
    //メッセージ設定
    StringBuilder sb = new StringBuilder();
    sb.append("社員ID：");
    sb.append(updUserId);
    sb.append("　　");
    sb.append(syainJouhouBLlist.get(0).getName());
    sb.append("さんの情報を更新しました");
    request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());

    //リクエストスコープにインスタンスを保存
    request.setAttribute(Path.SYAIN_HENSYU_SCOPE, syainJouhouBLlist);
    // 社員情報編集画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
    dispatcher.forward(request, response);

  }

}
