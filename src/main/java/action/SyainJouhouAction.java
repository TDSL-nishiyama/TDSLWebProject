package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Path;
import control.SyainJouhouDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SessionKanriBean;
import model.SyainJouhouEntity;

public class SyainJouhouAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 直接アクセスがあった場合、ログイン画面に遷移させる
		RequestDispatcher dispatcher = request.getRequestDispatcher(request
				.getContextPath() + Path.LOGIN_GAMEN);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ログインしているのか確認するため、セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		SessionKanriBean sessionKanriBean = (SessionKanriBean) session
				.getAttribute(Path.SESSION_SCOPE_NAME);

		if (sessionKanriBean == null) {
			// ログインしていない場合リダイレクト
			response.sendRedirect(request.getContextPath() + Path.LOGIN_GAMEN);
		} else {

			// セッションスコープにインスタンスを保存
			SyainJouhouDAO syainJouhouDAO = new SyainJouhouDAO();
			List<SyainJouhouEntity> syainJouhouEntityList = syainJouhouDAO
					.findAll(syainJouhouDAO);
			request.setAttribute(Path.SESSION_SCOPE_NAME,
					syainJouhouEntityList);
			// 結果出力画面(ResultSyainJouhou.jsp)にフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYAIN_JOUHOU_PATH);
			dispatcher.forward(request, response);
		}
	}
}