package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.ERRORMSG;
import constents.Const.Path;
import control.SkillBL;
import control.common.CheckCommon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SkillBean;

/**
 * Servlet implementation class SkillAction
 */
public class SkillAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //共通クラスのインスタンス化
    SkillBL skillBL = new SkillBL();
    CheckCommon checkC = new CheckCommon();

    //画面情報の取得
    String selId = request.getParameter("selId");
    int id = 0;
    if (checkC.checkBlankOrNULL(selId) == true) {
      id = Integer.parseInt(selId);
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

    //スキルシート閲覧画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.SKILLSHEET_GAMEN);
    dispatcher.forward(request, response);
  }

}
