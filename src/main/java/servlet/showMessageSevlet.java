package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("src/index.jsp")
public class showMessageSevlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setAttribute("こんにちは", "こんにちは");
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/src/showmessage.jsp");
        dispatcher.forward(request,response);

	}

}