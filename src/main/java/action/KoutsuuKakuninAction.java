package action;

import java.io.IOException;

import constents.Const.Path;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class KoutsuuKakuninAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //交通費精算確認画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTUSUU_KAKUNIN_GAMEN);
    dispatcher.forward(request, response);
	}

}
