package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.UserShousai;
import control.SyainJouhouBL;
import control.common.CastCommon;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;
import model.SyainJouhouHensyuuBean;

/**
 * Servlet implementation class ResultSyainJouhouHensyuuAction
 */
public class ResultSyainJouhouHensyuuIppanAction extends jakarta.servlet.http.HttpServlet {
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

    //共通クラスのインスタンス化
    CheckCommon checkCommon = new CheckCommon();
    CastCommon castCommon = new CastCommon();

    //画面情報の取得
    int updUserId = Integer.parseInt(request.getParameter("updUserId"));
    String sei = request.getParameter(UserShousai.GAMEN_SEI);
    String mei = request.getParameter(UserShousai.GAMEN_MEI);
    String seiyomi = request.getParameter(UserShousai.GAMEN_SEIYOMI);
    String meiyomi = request.getParameter(UserShousai.GAMEN_MEIYOMI);
    String syusshin = request.getParameter(UserShousai.GAMEN_SYUSSSHIN);
    String juusyo = request.getParameter(UserShousai.GAMEN_JUUSYO);
    //日付形式の取得・変換
    String nyuusyaYMD = castCommon.chgDStrToStr(request.getParameter(UserShousai.GAMEN_NYUUSYAYMD));
    
    /* エラーチェック　START */
    boolean errflg = false;
    
    //日付項目チェック
    errflg = checkCommon.checkDate(nyuusyaYMD);
    if (errflg == false) {
      //エラーがあった場合
      saveCatchParseException(request);
      //メッセージを格納
      StringBuilder sb = new StringBuilder();
      sb.append(ERRORMSG.ERROR_SETLENIENT);
      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
      //社員情報編集画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYAIN_HENSYU_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //更新情報の格納
    Map<String, Object> updKoumoku = new HashMap<String, Object>();
    updKoumoku.put(UserShousai.GAMEN_SEI, sei);
    updKoumoku.put(UserShousai.GAMEN_MEI, mei);
    updKoumoku.put(UserShousai.GAMEN_SEIYOMI, seiyomi);
    updKoumoku.put(UserShousai.GAMEN_MEIYOMI, meiyomi);
    updKoumoku.put(UserShousai.GAMEN_SYUSSSHIN, syusshin);
    updKoumoku.put(UserShousai.GAMEN_JUUSYO, juusyo);

    //必須項目確認処理
    //必須項目名をkey値、論理名をvalueとしてMAPに格納
    LinkedHashMap<String, String> hissuKoumoku = new LinkedHashMap<String, String>();
    UserShousai.hissuKoumokuPutIppan(hissuKoumoku);

    Map<String, Object> hissuCheck = checkCommon.checkHissu(updKoumoku, hissuKoumoku);
    errflg = (boolean) hissuCheck.get("errflg");

    //エラーがあった場合
    if (errflg == false) {
      //メッセージを格納
      StringBuilder sb = new StringBuilder();
      sb.append(MSG.MASTA_UPD_2_1);
      sb.append(hissuCheck.get("errMsgKoumoku").toString());
      sb.append(MSG.MASTA_UPD_2_2);
      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());

      //画面入力値を保持
      //NULL項目はブランクに変換
      sei = castCommon.nullToBlank(sei);
      mei = castCommon.nullToBlank(mei);
      seiyomi = castCommon.nullToBlank(seiyomi);
      meiyomi = castCommon.nullToBlank(meiyomi);
      syusshin = castCommon.nullToBlank(syusshin);
      juusyo = castCommon.nullToBlank(juusyo);

      //リクエストスコープに値を設定
      SyainJouhouHensyuuBean bean = new SyainJouhouHensyuuBean(Integer.parseInt(request.getParameter("updUserId")), sei, seiyomi, mei,
          meiyomi,nyuusyaYMD,syusshin,juusyo);
      List<SyainJouhouHensyuuBean> list = new ArrayList<SyainJouhouHensyuuBean>();
      list.add(bean);
      request.setAttribute(Path.SYAIN_HENSYU_SCOPE, list);

      //社員情報編集画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYAIN_HENSYU_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    /* エラーチェック　END */

    //データ更新処理
    SyainJouhouBL syainJouhouBL = new SyainJouhouBL(kanriFlg);
    try {
      syainJouhouBL.syainJouhouUpd(updKoumoku, updUserId);
    } catch (SQLException e) {
      //エラーメッセージを格納
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      //エラー画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //ユーザー情報を取得
    List<SyainJouhouHensyuuBean> syainJouhouBLlist = syainJouhouBL.resultSyainJouhouHensyu(updUserId);

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
        .getRequestDispatcher(Path.SYAIN_HENSYU_GAMEN);
    dispatcher.forward(request, response);

  }
  
  /**
   * 日付項目が変換できなかった際の画面項目値の保持用
   * @param request
   * @throws ServletException
   * @throws IOException
   */
  private void saveCatchParseException(HttpServletRequest request)
      throws ServletException, IOException {
    //画面から情報を取得、Mapに値を設定
    int userid = Integer.parseInt(request.getParameter("updUserId"));
    String sei = request.getParameter("sei");
    String mei = request.getParameter("mei");
    String seiyomi = request.getParameter("sei_yomi");
    String meiyomi = request.getParameter("mei_yomi");
    String nyuusyaYMD = request.getParameter("nyuusyaYMD");
    String seibetsu = request.getParameter("seibetsu");
    String syusshin = request.getParameter("syusshin");
    String juusyo = request.getParameter("juusyo");

    //画面入力値を保持
    //NULL項目はブランクに変換
    CastCommon castCommon = new CastCommon();
    sei = castCommon.nullToBlank(sei);
    mei = castCommon.nullToBlank(mei);
    seiyomi = castCommon.nullToBlank(seiyomi);
    meiyomi = castCommon.nullToBlank(meiyomi);
    nyuusyaYMD = castCommon.nullToBlank(nyuusyaYMD);
    seibetsu = castCommon.nullToBlank(seibetsu);
    syusshin = castCommon.nullToBlank(syusshin);
    juusyo = castCommon.nullToBlank(juusyo);

    //リクエストスコープに値を設定
    SyainJouhouHensyuuBean bean = new SyainJouhouHensyuuBean(userid, sei, seiyomi, mei, meiyomi, nyuusyaYMD, syusshin, juusyo);
    List<SyainJouhouHensyuuBean> list = new ArrayList<SyainJouhouHensyuuBean>();
    list.add(bean);
    request.setAttribute(Path.SYAIN_HENSYU_SCOPE, list);
  }
}
