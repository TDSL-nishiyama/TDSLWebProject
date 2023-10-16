package action;

import java.io.IOException;

import constents.Const.Path;
import control.UserDelBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserUpdateAction
 */
public class UserDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//ユーザー削除処理
		UserDelBL userDelBL = new UserDelBL();
		
		String beforeDel = "";
		//ユーザー一覧からの遷移

		//ユーザー削除からの遷移
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(beforeDel);
		dispatcher.forward(request, response);
	}

}
