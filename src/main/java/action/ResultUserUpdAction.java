package action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.UserShousai;
import control.UserUpdBL;
import control.common.CalcCommon;
import control.common.CastCommon;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaBean;

/**
 * ユーザー更新実行画面のサーブレット
 */
public class ResultUserUpdAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    UserUpdBL userUpdBL = new UserUpdBL();
    boolean errflg = false;
    int id = 0;
    Map<String, Object> gamenInfo = new LinkedHashMap<>();
    CheckCommon checkCommon = new CheckCommon();
    CastCommon castCommon = new CastCommon();
    CalcCommon calcCommon = new CalcCommon();

    //選択したユーザーIDがみつからない場合エラー
    if (request.getParameter("userIdUpd") != null) {
      id = Integer.parseInt(request.getParameter("userIdUpd"));
      gamenInfo.put("userIdUpd", String.valueOf(id));

      //入力されたIDが存在しない場合、メッセージを表示
      errflg = userUpdBL.userUpdCheck(id);
      if (errflg == false) {
        //メッセージを格納
        request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_UPD_1);
        //ユーザー更新画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.USER_UPD_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
    }

    //更新処理(ResultUserUpdhdn項目で判定)
    if (request.getParameter("hdnUserId") != null) {
      id = Integer.parseInt(request.getParameter("hdnUserId"));

      //画面から情報を取得、Mapに値を設定
      String userName = request.getParameter("userName");
      gamenInfo.put("userName", userName);
      String kanriFlg = request.getParameter("kanriFlg");
      gamenInfo.put("kanriFlg", kanriFlg);
      String sei = request.getParameter("sei");
      gamenInfo.put("sei", sei);
      String mei = request.getParameter("mei");
      gamenInfo.put("mei", mei);
      String seiyomi = request.getParameter("sei_yomi");
      gamenInfo.put("sei_yomi", seiyomi);
      String meiyomi = request.getParameter("mei_yomi");
      gamenInfo.put("mei_yomi", meiyomi);
      String nyuusyaYMD = request.getParameter("nyuusyaYMD");
      gamenInfo.put("nyuusyaYMD", nyuusyaYMD);
      String seibetsu = request.getParameter("seibetsu");
      gamenInfo.put("seibetsu", seibetsu);
      String seinenngappi = request.getParameter("seinenngappi");
      gamenInfo.put("seinenngappi", seinenngappi);
      String syusshin = request.getParameter("syusshin");
      gamenInfo.put("syusshin", syusshin);
      String juusyo = request.getParameter("juusyo");
      gamenInfo.put("juusyo", juusyo);
      
      /* エラーチェック　START */
      //日付項目チェック
      checkCommon.checkDate(nyuusyaYMD);
      checkCommon.checkDate(seinenngappi);
      if (errflg == true) {
        //エラーがあった場合
        saveCatchParseException(request);
        //メッセージを格納
        StringBuilder sb = new StringBuilder();
        sb.append(ERRORMSG.ERROR_SETLENIENT);
        request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
        //ユーザー更新実行画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.USER_UPD_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
      
      //必須項目チェック
      //必須項目名をkey値、論理名をvalueとしてMAPに格納
      Map<String, String> hissuKoumoku = new LinkedHashMap<>();
      hissuKoumoku.put("username", "ユーザー名");
      hissuKoumoku.put("kanriFlg", "管理者権限");
      //ユーザー詳細テーブル分を設定
      UserShousai.hissuKoumokuPut(hissuKoumoku);

      Map<String, Object> hissuCheck = checkCommon.checkHissu(gamenInfo, hissuKoumoku);
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
        MastaBean bean = new MastaBean(userName, Boolean.valueOf(kanriFlg), sei, seiyomi, mei, meiyomi, nyuusyaYMD,
            seibetsu, seinenngappi, syusshin, juusyo);
        List<MastaBean> list = new ArrayList<MastaBean>();
        list.add(bean);
        request.setAttribute(Path.USER_UPD_SCOPE, list);

        //ユーザー更新実行画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
        dispatcher.forward(request, response);
        return;
      }
      
      if (nyuusyaYMD != null) {
        //日付整合性チェック1（生年月日より入社日付のほうが大きい場合エラー）
        if (checkCommon.diffDate1Date2(seinenngappi, nyuusyaYMD) == false) {
          //日付整合性チェック2（生年月日-入社日付が16未満の場合エラー（15歳以下の就業は不可能なため））
          Period pr = calcCommon.diffDate(seinenngappi, nyuusyaYMD);
          if (pr.getYears() < 16) {
            //画面入力値を保持
            saveCatchParseException(request);
            //メッセージを格納
            StringBuilder sb = new StringBuilder();
            sb.append(MSG.MSG_DATE_INTEGRITY_ERR);
            request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
            //ユーザー更新実行画面に遷移
            RequestDispatcher dispatcher = request
                .getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
            dispatcher.forward(request, response);
            return;
          }
        }
      }
      /* エラーチェック　END */

      //更新の実行
      try {
        userUpdBL.updUserList(gamenInfo, id);
      } catch (SQLException e) {
        //エラーメッセージを格納
        request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
        //エラー画面に遷移
        RequestDispatcher dispatcher = request
            .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
        dispatcher.forward(request, response);
        return;
      }

      //メッセージ設定
      StringBuilder sb = new StringBuilder();
      sb.append("社員ID：");
      sb.append(id);
      sb.append("　　");
      sb.append(userName);
      sb.append("さんの情報を更新しました");

      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());

      //ユーザー更新画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_UPD_GAMEN);
      dispatcher.forward(request, response);
    }

    //リクエストスコープにインスタンスを保存
    List<MastaBean> userUpdList = userUpdBL.selectUserList(gamenInfo);
    request.setAttribute(Path.USER_UPD_SCOPE, userUpdList);

    //ユーザー更新実行画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
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
    String userName = request.getParameter("userName");
    String kanriFlg = request.getParameter("kanriFlg");
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
    MastaBean bean = new MastaBean(userName, Boolean.valueOf(kanriFlg), sei, seiyomi, mei, meiyomi, nyuusyaYMD,
        seibetsu, seinenngappi, syusshin, juusyo);
    List<MastaBean> list = new ArrayList<MastaBean>();
    list.add(bean);
    request.setAttribute(Path.USER_UPD_SCOPE, list);
  }

}