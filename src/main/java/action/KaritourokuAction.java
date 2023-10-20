package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Path;
import control.UserIchiranBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MastaEntity;

/**
 * ユーザー登録画面のサーブレット
 */
public class KaritourokuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserIchiranBL userIchiranBL = new UserIchiranBL();		

		//リクエストスコープにインスタンスを保存
		List<MastaEntity> userIchiranBLlist = userIchiranBL.resultKaritourokuUserList(userIchiranBL);
		request.setAttribute(Path.USER_ICHIRAN_SCOPE, userIchiranBLlist);
		
		//ユーザー一覧画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.KARI_USER_GAMEN);
		dispatcher.forward(request, response);
	}

}
