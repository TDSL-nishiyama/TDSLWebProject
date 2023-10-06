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
public class UserAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("userName");
		String kanriFlg = request.getParameter("kanriFlg");
		//ユーザー登録
		UserAddBL userAddBL = new UserAddBL();
		userAddBL.userAdd(name, Boolean.valueOf(kanriFlg));

		//登録完了メッセージ
		request.setAttribute("MSG", "ユーザー登録が完了しました");
		//マスタ画面に遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.MASTA_GAMEN);
		dispatcher.forward(request, response);
	}

}
