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

    Map<String, Object> gamenInfo = new HashMap<String, Object>();
    //日付項目は空白の場合NULLに設定したうえで格納
    gamenInfo.put(SkillHensyuuG.GAMEN_USERID, id);
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU1YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU1YMD)));
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU1, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU1));
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU2YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU2YMD)));
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU2, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU2));
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU3YMD, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_SIKAKU3YMD)));
    gamenInfo.put(SkillHensyuuG.GAMEN_SIKAKU3, request.getParameter(SkillHensyuuG.GAMEN_SIKAKU3));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER1_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_START)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER1_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_END)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER1, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER1_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_POS));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER1_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER1_TECH));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER2_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_START)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER2_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_END)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER2, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER2_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_POS));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER2_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER2_TECH));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER3_START, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_START)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER3_END, castC.setNull(request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_END)));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER3, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER3_POS, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_POS));
    gamenInfo.put(SkillHensyuuG.GAMEN_CARRIER3_TECH, request.getParameter(SkillHensyuuG.GAMEN_CARRIER3_TECH));

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
