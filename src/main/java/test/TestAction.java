package test;

import java.io.IOException;

import test.TestBL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestAction
 */
public class TestAction extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor. 
   */
  public TestAction() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    TestBL testBL = new TestBL();
    var result = testBL.testBLMain();
    request.setAttribute("KOUMOKU", "平均年齢");
    request.setAttribute("TEST", result);
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/testpage.jsp");
    dispatcher.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
