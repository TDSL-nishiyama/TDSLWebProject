package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaBean;

/**
 * ユーザー登録実行画面のサーブレット
 */
public class UserAddAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    //スコープエリアの作成
    MastaBean bean = new MastaBean("",false,"","","","",null,"",null,"","");
    List<MastaBean> list = new ArrayList<MastaBean>();
    list.add(bean);
    request.setAttribute(Path.USER_ADD_SCOPE, list);
    
    //マスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ADD_GAMEN);
    dispatcher.forward(request, response);
  }

}
