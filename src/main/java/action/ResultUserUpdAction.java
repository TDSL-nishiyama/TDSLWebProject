package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constents.Const.MSG;
import constents.Const.Path;
import control.UserUpdBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaEntity;

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

		Map<String, String> updKoumoku = new HashMap<String, String>();
		UserUpdBL userUpdBL = new UserUpdBL();

		//選択したユーザーID
		String id = request.getParameter("userIdUpd");
		updKoumoku.put("userIdUpd", id);
		
		//更新処理(ResultUserUpdhdn項目で判定)
		if (request.getParameter("hdnUserId") != null) {
			id = request.getParameter("hdnUserId");
			String name = request.getParameter("username");
			String kanriflg = request.getParameter("kanriflg");
			
			updKoumoku.put("userIdUpd", id);
			updKoumoku.put("username", name);
			updKoumoku.put("kanriflg", kanriflg);

			//ユーザー情報のアップデート
			userUpdBL.updUserList(updKoumoku);
			
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
		List<MastaEntity> userUpdList = userUpdBL.resultUserList(userUpdBL, updKoumoku);
		request.setAttribute(Path.USER_HENSYU_SCOPE, userUpdList);

		//ユーザー更新実行画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.RESULT_USER_UPD_GAMEN);
		dispatcher.forward(request, response);
	}

}