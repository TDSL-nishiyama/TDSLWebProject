package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Path;
import control.UserUpdBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaEntity;

/**
 * ユーザー登録画面のサーブレット
 */
public class ResultUserUpdAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    //選択したユーザーID
    int id = Integer.parseInt(request.getParameter("userIdUpd"));
    
    UserUpdBL userUpdBL = new UserUpdBL();    

    //リクエストスコープにインスタンスを保存
    List<MastaEntity> userUpdList = userUpdBL.resultUserList(userUpdBL,id);
    request.setAttribute(Path.USER_ICHIRAN_SCOPE, userUpdList);
    
    //ユーザー更新実行画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
    dispatcher.forward(request, response);
  }

}