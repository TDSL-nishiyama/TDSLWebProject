package action;

import java.io.IOException;
import java.util.List;

import constents.Const.Path;
import control.SyainJouhouBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SyainJouhouEntity;

public class SyainJouhouAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			//TODO 管理者フラグ取得できてない。セッションからとったほうがよさそう
			//管理者フラグの取得
			boolean KanriFlg = false;
			final String KANRIFLG_TRUE  = "SyainKanrisya";
			if(request.getParameter("name").equals(KANRIFLG_TRUE)){
				KanriFlg = true;
			}
			//データ格納処理
			SyainJouhouBL syainJouhouBL = new SyainJouhouBL(KanriFlg);
		
			//リクエストスコープにインスタンスを保存
			List<SyainJouhouEntity> SyainJouhouBLlist = syainJouhouBL.resultSyainList(syainJouhouBL);
			request.setAttribute(Path.SYAIN_JOUHOU_SCOPE,SyainJouhouBLlist);
			// 結果出力画面(ResultSyainJouhou.jsp)にフォワード
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Path.SYAIN_JOUHOU_PATH);
			dispatcher.forward(request, response);
		}
	}