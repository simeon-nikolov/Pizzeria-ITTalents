package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/profile.jsp");
		dispatcher.forward(request, response);
	}

}
