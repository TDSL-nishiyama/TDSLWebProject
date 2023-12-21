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

/**
 * Servlet implementation class KoutsuuYoukyuuAction
 */
public class KoutsuuYoukyuuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //テキストボックスの初期値をリクエストスコープに設定
	  List<String> sendList = new ArrayList<String>();
	  final int GAMEN_KOUMOKU_KAZU = 10; //画面項目数分ブランクを挿入
	  for(int i = 0; i < GAMEN_KOUMOKU_KAZU; i++) {
	    sendList.add("");
	  }
	  request.setAttribute(Path.KOUTSUU_YOUKYU_SCOPE, sendList);
	  
    //交通費精算要求画面に遷移
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(Path.KOUTSUU_YOUKYUU_GAMEN);
    dispatcher.forward(request, response);
	}

}
