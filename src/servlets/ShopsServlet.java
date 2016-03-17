package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopsServlet
 */
@WebServlet("/shops")
public class ShopsServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("auth", super.isAuthenticated(request));
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/shops.jsp");
		dispatcher.forward(request, response);
	}
}
