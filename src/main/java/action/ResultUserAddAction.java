package action;

import java.io.IOException;
import java.util.LinkedHashMap;
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
    Map<String, String> gamenInfo = new LinkedHashMap<>();
    gamenInfo.put("username", request.getParameter("userName"));
    gamenInfo.put("sei", request.getParameter("sei"));
    gamenInfo.put("mei", request.getParameter("mei"));
    gamenInfo.put("sei_yomi", request.getParameter("sei_yomi"));
    gamenInfo.put("mei_yomi", request.getParameter("mei_yomi"));
    gamenInfo.put("nyuusyaYMD", request.getParameter("nyuusyaYMD"));
    gamenInfo.put("seibetsu", request.getParameter("seibetsu"));
    gamenInfo.put("seinenngappi", request.getParameter("seinenngappi"));
    gamenInfo.put("syusshin", request.getParameter("syusshin"));
    gamenInfo.put("juusyo", request.getParameter("juusyo"));

    //インスタンス化
    UserAddBL userAddBL = new UserAddBL();

    //必須項目チェック
    boolean errflg = false;
    String errMsgKoumoku = "";
    //必須項目名をkey値、論理名をvalueとしてMAPに格納
    Map<String, String> hissuKoumoku = new LinkedHashMap<>();
    hissuKoumoku.put("username", "ユーザー名");
    hissuKoumoku.put("sei", "姓");
    hissuKoumoku.put("mei", "名");
    hissuKoumoku.put("sei_yomi", "姓(ﾖﾐ)");
    hissuKoumoku.put("mei_yomi", "名(ﾖﾐ)");
    hissuKoumoku.put("seibetsu", "性別");
    hissuKoumoku.put("seinenngappi", "生年月日");
    hissuKoumoku.put("juusyo", "住所");

    //画面項目のvalue値分検索、必須項目のkey値と一致した場合、
    //必須チェックを行いエラーだった場合、必須項目のValueをメッセージとして格納
    loop: for (String gamen : gamenInfo.keySet()) {
      for (String hissu : hissuKoumoku.keySet())
        if (gamen == hissu) {
          errflg = userAddBL.checkHissu(gamenInfo.get(gamen));
          if (errflg == false) {
            errMsgKoumoku = hissuKoumoku.get(hissu);
            break loop;
          }
        }
    }

    if (errflg == false) {
      //メッセージを格納
      StringBuilder sb = new StringBuilder();
      sb.append(MSG.MASTA_ADD_1_1);
      sb.append(errMsgKoumoku);
      sb.append(MSG.MASTA_ADD_1_2);
      request.setAttribute(MSG.MSG_ATTRIBUTE, sb.toString());
      //ユーザー登録画面に遷移
      RequestDispatcher dispatcher = request
          .getRequestDispatcher(Path.USER_ADD_GAMEN);
      dispatcher.forward(request, response);
      return;
    }

    //ユーザー登録
    userAddBL.addUser(gamenInfo);

    //登録完了メッセージ
    request.setAttribute(MSG.MSG_ATTRIBUTE, MSG.MASTA_ADD_2);
    //マスタ画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.USER_ADD_GAMEN);
    dispatcher.forward(request, response);
  }

}