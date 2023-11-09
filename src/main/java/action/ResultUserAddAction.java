package action;

import java.io.IOException;

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
    
    String name = request.getParameter("userName");
    String kanriFlg = request.getParameter("kanriFlg");
    boolean pkanriFlg = false;
    
    if(kanriFlg.equals("1")) {
      pkanriFlg = true;
    }
    
    //ユーザー登録
    UserAddBL userAddBL = new UserAddBL();
    userAddBL.userAdd(name, pkanriFlg);

    //登録完了メッセージ
    request.setAttribute("MSG", "ユーザー登録が完了しました");
    //マスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ADD_GAMEN);
    dispatcher.forward(request, response);
  }

}