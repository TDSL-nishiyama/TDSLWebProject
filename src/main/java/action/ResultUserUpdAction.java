package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import constents.Const.MSG;
import constents.Const.Path;
import control.SyainJouhouBL;
import control.UserUpdBL;
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

    Map<String, Object> updKoumoku = new HashMap<String, Object>();
    UserUpdBL userUpdBL = new UserUpdBL();

    //選択したユーザーID
    int id = Integer.parseInt(request.getParameter("userIdUpd"));
    updKoumoku.put("userIdUpd", id);
    
    boolean errflg = false;
    //入力されたIDが存在しない場合、メッセージを表示
    errflg = userUpdBL.userUpdCheck(id);
    if (errflg == false) {
      //メッセージを格納
      request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_UPD_1);
      //ユーザー削除画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_UPD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //更新処理(ResultUserUpdhdn項目で判定)
    if (request.getParameter("hdnUserId") != null) {
      id = Integer.parseInt(request.getParameter("hdnUserId"));
      String name = request.getParameter("username");
      String kanriflg = request.getParameter("kanriflg");

      //画面情報の格納(userテーブル)
      updKoumoku.put("userIdUpd", id);
      updKoumoku.put("username", name);
      updKoumoku.put("kanriflg", kanriflg);

      //ユーザー情報のアップデート(userテーブル)実行
      userUpdBL.updUserList(updKoumoku);

      //画面情報の格納(usershousaiテーブル)
      updKoumoku.put("username", request.getParameter("username"));
      updKoumoku.put("sei", request.getParameter("sei"));
      updKoumoku.put("mei", request.getParameter("mei"));
      updKoumoku.put("sei_yomi", request.getParameter("sei_yomi"));
      updKoumoku.put("mei_yomi", request.getParameter("mei_yomi"));
      updKoumoku.put("nyuusyaYMD", request.getParameter("nyuusyaYMD"));
      updKoumoku.put("seibetsu", request.getParameter("seibetsu"));
      updKoumoku.put("seinenngappi", request.getParameter("seinenngappi"));
      updKoumoku.put("syusshin", request.getParameter("syusshin"));
      updKoumoku.put("juusyo", request.getParameter("juusyo"));

      //ユーザー詳細情報のアップデート（usershousaiテーブル）実行
      SyainJouhouBL syainJouhouBL = new SyainJouhouBL(Boolean.valueOf(kanriflg));
      syainJouhouBL.syainJouhouUpd(updKoumoku, id);

      //メッセージ設定
      StringBuilder sb = new StringBuilder();
      sb.append("社員ID：");
      sb.append(id);
      sb.append("　　");
      sb.append(name);
      sb.append("さんの情報を更新しました");

      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
    }

    //リクエストスコープにインスタンスを保存
    List<MastaBean> userUpdList = userUpdBL.selectUserList(updKoumoku);
    request.setAttribute(Path.USER_HENSYU_SCOPE, userUpdList);

    //ユーザー更新実行画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
    dispatcher.forward(request, response);
  }

}