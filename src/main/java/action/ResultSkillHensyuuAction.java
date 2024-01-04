package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constents.Const.ERRORMSG;
import constents.Const.MSG;
import constents.Const.Path;
import constents.SkillConst.SkillHensyuuG;
import control.SkillBL;
import control.common.CastCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SkillBean;

/**
 * Servlet implementation class SkillAction
 */
public class ResultSkillHensyuuAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    SkillBL skillBL = new SkillBL();
    CastCommon castC = new CastCommon();

    //画面情報の取得
    String selId = request.getParameter("selId");
    int id = Integer.parseInt(selId);
    List<Map<String, Object>> gamenInfo = new ArrayList<Map<String, Object>>();
    Map<String, Object> gamenVal1 = new HashMap<String, Object>();
    Map<String, Object> gamenVal2 = new HashMap<String, Object>();
    Map<String, Object> gamenVal3 = new HashMap<String, Object>();
    //日付項目は空白の場合NULLに設定したうえで格納
    gamenVal1.put(SkillHensyuuG.GAMEN_USERID, id);
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU1YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU1YMD)));
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU1, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU1));
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU2YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU2YMD)));
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU2, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU2));
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU3YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU3YMD)));
    gamenVal1.put(SkillHensyuuG.GAMEN_SIKAKU3, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU3));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER1_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_START)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER1_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_END)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER1, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER1_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_POS));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER1_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_TECH));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER2_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_START)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER2_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_END)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER2, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER2_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_POS));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER2_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_TECH));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER3_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_START)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER3_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_END)));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER3, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER3_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_POS));
    gamenVal1.put(SkillHensyuuG.GAMEN_CARRIER3_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_TECH));
    gamenInfo.add(gamenVal1);
    //職歴No4以降はSkill_2テーブルに登録
    gamenVal2.put(SkillHensyuuG.GAMEN_USERID, id);
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER1_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_START_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER1_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_END_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER1, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER1_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_POS_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER1_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_TECH_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER2_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_START_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER2_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_END_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER2, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER2_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_POS_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER2_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_TECH_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER3_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_START_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER3_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_END_2)));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER3, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER3_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_POS_2));
    gamenVal2.put(SkillHensyuuG.GAMEN_CARRIER3_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_TECH_2));
    gamenInfo.add(gamenVal2);
    //職歴No7以降はSkill_3テーブルに登録
    gamenVal3.put(SkillHensyuuG.GAMEN_USERID, id);
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER1_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_START_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER1_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_END_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER1, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER1_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_POS_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER1_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_TECH_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER2_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_START_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER2_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_END_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER2, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER2_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_POS_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER2_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_TECH_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER3_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_START_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER3_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_END_3)));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER3, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER3_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_POS_3));
    gamenVal3.put(SkillHensyuuG.GAMEN_CARRIER3_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_TECH_3));
    gamenInfo.add(gamenVal3);

    //更新処理
    try {
      skillBL.updSkill(id, gamenInfo);
    } catch (SQLException e) {
      //システムエラー画面に遷移
    }

    //リクエストスコープにインスタンスを保存
    List<SkillBean> skillBLList = new ArrayList<SkillBean>();
    try {
      skillBLList = skillBL.getSkill(id);
    } catch (SQLException e) {
      //エラー画面に遷移
      request.setAttribute(ERRORMSG.ERRMSG_ATTRIBUTE, ERRORMSG.DBERROR);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.SYSTEM_ERROR_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    request.setAttribute(Path.SKILL_SCOPE, skillBLList);

    //更新完了メッセージの設定
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.SK_HENSYU_1);

    //スキルシート閲覧画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.SKILLSHEET_GAMEN);
    dispatcher.forward(request, response);
  }

}
