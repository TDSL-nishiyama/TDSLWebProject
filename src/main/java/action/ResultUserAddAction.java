package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    //ユーザー登録
    UserAddBL userAddBL = new UserAddBL();
    userAddBL.userAdd(gamenInfo);

    //登録完了メッセージ
    request.setAttribute("MSG", "ユーザー登録が完了しました");
    //マスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ADD_GAMEN);
    dispatcher.forward(request, response);
  }

}