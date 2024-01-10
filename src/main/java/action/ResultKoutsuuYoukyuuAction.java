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
import constents.KoutsuuConst.YoukyuuG;
import control.KoutsuuBL;
import control.common.CastCommon;
import control.common.CheckCommon;
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
    CheckCommon checkC = new CheckCommon();

    //画面情報を取得
    String riyouhiduke1 = castC.chgDStrToStr(request.getParameter(YoukyuuG.GAMEN_RIYOUHIDUKE_1));
    String kukans1 = request.getParameter(YoukyuuG.GAMEN_KUKAN_START_1);
    String kukane1 = request.getParameter(YoukyuuG.GAMEN_KUKAN_END_1);
    String kingaku1 = request.getParameter(YoukyuuG.GAMEN_KINGAKU_1);
    String bikou1 = request.getParameter(YoukyuuG.GAMEN_BIKOU_1);
    String riyouhiduke2 = castC.chgDStrToStr(request.getParameter(YoukyuuG.GAMEN_RIYOUHIDUKE_2));
    String kukans2 = request.getParameter(YoukyuuG.GAMEN_KUKAN_START_2);
    String kukane2 = request.getParameter(YoukyuuG.GAMEN_KUKAN_END_2);
    String kingaku2 = request.getParameter(YoukyuuG.GAMEN_KINGAKU_2);
    String bikou2 = request.getParameter(YoukyuuG.GAMEN_BIKOU_2);

    //エラーチェックSTART
    boolean errFlg = false;

    //日付項目チェック
    if (checkC.checkDate(riyouhiduke1) == false || checkC.checkDate(riyouhiduke2) == false) {
      //メッセージを格納
      StringBuilder msg = new StringBuilder();
      msg.append(ERRORMSG.ERROR_SETLENIENT);
      //エラーがあった場合
      forwardErrorCase(request,response,msg.toString());
      return;
    }

    //必須チェック
    //画面項目の格納
    Map<String, Object> gamenInfo = new LinkedHashMap<String, Object>();
    gamenInfo.put("riyouhiduke", riyouhiduke1);
    gamenInfo.put("kukans", kukans1);
    gamenInfo.put("kukane", kukane1);
    gamenInfo.put("kingaku", kingaku1);
    //チェック項目の格納
    Map<String, String> checkKoumoku = new LinkedHashMap<String, String>();
    checkKoumoku.put("riyouhiduke", "利用日付");
    checkKoumoku.put("kukans", "開始区間");
    checkKoumoku.put("kukane", "終了区間");
    checkKoumoku.put("kingaku", "金額");

    Map<String, Object> hissuCheck = new HashMap<String, Object>();
    hissuCheck = checkC.checkHissu(gamenInfo, checkKoumoku);

    if ((boolean) hissuCheck.get("errflg") == false) {
      StringBuilder msg = new StringBuilder();
      msg.append(hissuCheck.get("errMsgKoumoku"));
      msg.append("１を入力してください");
      forwardErrorCase(request, response, msg.toString());
      return;
    }
    
    //区間項目チェック
    errFlg = koutsuuBL.checkKukan(kukans1, kukane1);
    if (errFlg == true) {
      StringBuilder msg = new StringBuilder();
      msg.append("開始区間１と終了区間１が同一となっています");
      forwardErrorCase(request, response, msg.toString());
      return;
    }
    
    //_2画面項目の格納
    gamenInfo = new LinkedHashMap<String, Object>();
    gamenInfo.put("riyouhiduke", riyouhiduke2);
    gamenInfo.put("kukans", kukans2);
    gamenInfo.put("kukane", kukane2);
    gamenInfo.put("kingaku", kingaku2);
    gamenInfo.put("bikou", bikou2);
    //_2のいずれかの項目に入力がある場合は_2の項目に対しても必須チェック・区間チェックを行う
    boolean _2Flg = Boolean.valueOf((koutsuuBL.check_2(gamenInfo)).get("_2Flg"));
    if (_2Flg == true) {
      hissuCheck = new HashMap<String, Object>();
      hissuCheck = checkC.checkHissu(gamenInfo, checkKoumoku);

      if ((boolean) hissuCheck.get("errflg") == false) {
        StringBuilder msg = new StringBuilder();
        msg.append(hissuCheck.get("errMsgKoumoku"));
        msg.append("２を入力してください");
        forwardErrorCase(request, response, msg.toString());
        return;
      }
      
      //区間項目チェック
      errFlg = koutsuuBL.checkKukan(kukans2, kukane2);
      if (errFlg == true) {
        StringBuilder msg = new StringBuilder();
        msg.append("開始区間２と終了区間２が同一となっています");
        forwardErrorCase(request, response, msg.toString());
        return;
      }
    }
    //エラーチェックEND

    //画面の値をKoutsuuBeanに格納
    KoutsuuBean bean1 = new KoutsuuBean(selId, castC.chgStrtoLDT(riyouhiduke1), kukans1, kukane1, kingaku1, bikou1);

    //更新処理
    List<KoutsuuBean> list = new ArrayList<KoutsuuBean>();
    list.add(bean1);
    //_2に入力がある場合
    if (_2Flg == true) {
      //画面の値をKoutsuuBeanに格納
      KoutsuuBean bean2 = new KoutsuuBean(selId, castC.chgStrtoLDT(riyouhiduke2), kukans2, kukane2, kingaku2, bikou2);
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
    //テキストボックスの初期値をリクエストスコープに設定
    List<String> sendList = new ArrayList<String>();
    final int GAMEN_KOUMOKU_KAZU = 10; //画面項目数分ブランクを挿入
    for(int i = 0; i < GAMEN_KOUMOKU_KAZU; i++) {
      sendList.add("");
    }
    request.setAttribute(Path.KOUTSUU_YOUKYU_SCOPE, sendList);
    //メッセージ格納
    StringBuilder sb = new StringBuilder();
    sb.append(MSG.K_YOUKYU_1);
    request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
    //交通費精算要求画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_YOUKYUU_GAMEN);
    dispatcher.forward(request, response);
    return;
  }
  
  /**
   * {@index エラー時の画面項目保持用}
   * @param request
   * @param response
   * @param msg　エラーメッセージ
   * @throws ServletException
   * @throws IOException
   */
  private void forwardErrorCase(HttpServletRequest request, HttpServletResponse response, String msg)
      throws ServletException, IOException {
    //画面入力値の保持
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
    //リクエストスコープに値を保存
    List<String> sendList = new ArrayList<String>();
    CastCommon castC = new CastCommon();
    sendList.add(castC.nullToBlank(riyouhiduke1));
    sendList.add(castC.nullToBlank(kukans1));
    sendList.add(castC.nullToBlank(kukane1));
    sendList.add(castC.nullToBlank(kingaku1));
    sendList.add(castC.nullToBlank(bikou1));
    sendList.add(castC.nullToBlank(riyouhiduke2));
    sendList.add(castC.nullToBlank(kukans2));
    sendList.add(castC.nullToBlank(kukane2));
    sendList.add(castC.nullToBlank(kingaku2));
    sendList.add(castC.nullToBlank(bikou2));
    
    request.setAttribute(Path.KOUTSUU_YOUKYU_SCOPE, sendList);
    
    //メッセージ格納
    request.setAttribute(MSG.MSG_ATTRIBUTE, msg);
    //交通費精算要求画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_YOUKYUU_GAMEN);
    dispatcher.forward(request, response);
    return;
  }

}
