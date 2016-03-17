package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/send")
public class SendEmailServlet extends BaseHttpServlet {
	private static final String DEFAULT_ADMIN_EMAIL = "mitov.christian@gmail.com";
	private static final long serialVersionUID = 1L;

	public SendEmailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String from = DEFAULT_ADMIN_EMAIL;
		String to = request.getParameter("email");
		String message = request.getParameter("message");
	}

}
