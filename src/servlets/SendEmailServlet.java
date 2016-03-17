package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/send")
public class SendEmailServlet extends BaseHttpServlet {
	private static final String DEFAULT_ADMIN_EMAIL = "pizzabug1@gmail.com";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = "pizzabug1@gmail.com";
		String pass = "pizza12345";
		String from = DEFAULT_ADMIN_EMAIL;
		String to = request.getParameter("email");
		String text = request.getParameter("message");

		

		
	}

}
