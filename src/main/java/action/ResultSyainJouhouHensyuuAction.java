package action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Period;
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
import control.common.CalcCommon;
import control.common.CastCommon;
import control.common.CheckCommon;
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

    //共通クラスのインスタンス化
    CheckCommon checkCommon = new CheckCommon();
    CastCommon castCommon = new CastCommon();
    CalcCommon calcCommon = new CalcCommon();

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

    /* エラーチェック　START */
    boolean errflg = false;

    //日付項目チェック
    errflg = checkCommon.checkDate(nyuusyaYMD);
    errflg = checkCommon.checkDate(seinenngappi);
    if (errflg == false) {
      //エラーがあった場合
      saveCatchParseException(request);
      //メッセージを格納
      StringBuilder sb = new StringBuilder();
      sb.append(ERRORMSG.ERROR_SETLENIENT);
      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
      //社員情報編集画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
      dispatcher.forward(request, response);
      return;
    }

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

    //必須項目確認処理
    //必須項目名をkey値、論理名をvalueとしてMAPに格納
    LinkedHashMap<String, String> hissuKoumoku = new LinkedHashMap<String, String>();
    UserShousai.hissuKoumokuPut(hissuKoumoku);

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
      seibetsu = castCommon.nullToBlank(seibetsu);
      syusshin = castCommon.nullToBlank(syusshin);
      juusyo = castCommon.nullToBlank(juusyo);

      //リクエストスコープに値を設定
      SyainJouhouBean bean = new SyainJouhouBean(Integer.parseInt(request.getParameter("updUserId")), sei, seiyomi, mei,
          meiyomi, nyuusyaYMD, seibetsu, seinenngappi, syusshin, juusyo);
      List<SyainJouhouBean> list = new ArrayList<SyainJouhouBean>();
      list.add(bean);
      request.setAttribute(Path.SYAIN_HENSYU_SCOPE, list);

      //社員情報編集画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
      dispatcher.forward(request, response);
      return;
    }

    //入社年月日の項目が空白ではない場合
    if (!(nyuusyaYMD.equals("")) || nyuusyaYMD != null) {
      Period pr = calcCommon.diffDate(seinenngappi, nyuusyaYMD);
      StringBuilder sb = new StringBuilder();
      //日付整合性チェック1（生年月日より入社日付のほうが大きい場合エラー）
      if (pr.getDays() < 0) {
        //画面入力値を保持
        saveCatchParseException(request);
        //メッセージを格納
        sb.append(MSG.MSG_DATE_INTEGRITY_ERR_1);
        request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
        //ユーザー更新実行画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
        dispatcher.forward(request, response);
        return;
      }
      //日付整合性チェック2（生年月日-入社日付が16未満の場合エラー（15歳以下の就業は不可能なため））
      if (pr.getYears() < 16) {
        //画面入力値を保持
        saveCatchParseException(request);
        //メッセージを格納
        sb.append(MSG.MSG_DATE_INTEGRITY_ERR_2);
        request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
        //社員情報編集画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYAIN_HENSYU_PATH);
        dispatcher.forward(request, response);
        return;
      }
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
    List<SyainJouhouBean> syainJouhouBLlist = syainJouhouBL.resultSyainJouhouHensyu(updUserId);

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
    String userName = request.getParameter("userName");
    String sei = request.getParameter("sei");
    String mei = request.getParameter("mei");
    String seiyomi = request.getParameter("sei_yomi");
    String meiyomi = request.getParameter("mei_yomi");
    String nyuusyaYMD = request.getParameter("nyuusyaYMD");
    String seibetsu = request.getParameter("seibetsu");
    String seinenngappi = request.getParameter("seinenngappi");
    String syusshin = request.getParameter("syusshin");
    String juusyo = request.getParameter("juusyo");

    //画面入力値を保持
    //NULL項目はブランクに変換
    CastCommon castCommon = new CastCommon();
    userName = castCommon.nullToBlank(userName);
    sei = castCommon.nullToBlank(sei);
    mei = castCommon.nullToBlank(mei);
    seiyomi = castCommon.nullToBlank(seiyomi);
    meiyomi = castCommon.nullToBlank(meiyomi);
    nyuusyaYMD = castCommon.nullToBlank(nyuusyaYMD);
    seibetsu = castCommon.nullToBlank(seibetsu);
    seinenngappi = castCommon.nullToBlank(seinenngappi);
    syusshin = castCommon.nullToBlank(syusshin);
    juusyo = castCommon.nullToBlank(juusyo);

    //リクエストスコープに値を設定
    SyainJouhouBean bean = new SyainJouhouBean(userid, userName, sei, seiyomi, mei, meiyomi, nyuusyaYMD,
        seibetsu, seinenngappi, syusshin, juusyo);
    List<SyainJouhouBean> list = new ArrayList<SyainJouhouBean>();
    list.add(bean);
    request.setAttribute(Path.SYAIN_HENSYU_SCOPE, list);
  }
}
