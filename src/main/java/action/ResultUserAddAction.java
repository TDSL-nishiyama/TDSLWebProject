package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import constents.Const.MSG;
import constents.Const.Path;
import control.UserAddBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ユーザー登録画面のサーブレット
 */
public class ResultUserAddAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //リクエスト画面から情報を取得
    Map<String, String> gamenInfo = new HashMap<>();
    gamenInfo.put("username", request.getParameter("userName"));
    gamenInfo.put("sei", request.getParameter("sei"));
    gamenInfo.put("mei", request.getParameter("mei"));
    gamenInfo.put("seiyomi", request.getParameter("seiyomi"));
    gamenInfo.put("meiyomi", request.getParameter("meiyomi"));
    gamenInfo.put("nyuusyaYMD", request.getParameter("nyuusyaYMD"));
    gamenInfo.put("seibetsu", request.getParameter("seibetsu"));
    gamenInfo.put("seinenngappi", request.getParameter("seinenngappi"));
    gamenInfo.put("syusshin", request.getParameter("syusshin"));
    gamenInfo.put("juusyo", request.getParameter("juusyo"));
    
    //インスタンス化
    UserAddBL userAddBL = new UserAddBL();
    
    //必須項目チェック
    boolean errflg = false;
    String userName = gamenInfo.get("username");
    errflg = userAddBL.userAddCheck(userName);
    if (errflg == false) {
      //メッセージを格納
      StringBuilder sb = new StringBuilder();
      sb.append(MSG.MASTA_ADD_1_1);
      sb.append("userName");
      sb.append(MSG.MASTA_ADD_1_2);
      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
      //ユーザー登録画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_ADD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }
    
    //ユーザー登録
    userAddBL.userAdd(gamenInfo);

    //登録完了メッセージ
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_ADD_2);
    //マスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ADD_GAMEN);
    dispatcher.forward(request, response);
  }

}