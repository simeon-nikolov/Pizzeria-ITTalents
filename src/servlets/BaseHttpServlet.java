package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pizzeria.account.Account;

@WebServlet(description = "Base (abstract) Servlet that will have common methods")
public abstract class BaseHttpServlet extends HttpServlet {
	public static final String LOGGED_USER_ATTRIBUTE_NAME = "loggedUser";
	private static final long serialVersionUID = 1L;

	public boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session == null || session.isNew()) {
			return false;
		}
		
		if (session.getAttribute(LOGGED_USER_ATTRIBUTE_NAME) != null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session == null || session.isNew()) {
			return false;
		}
		
		Account acc = (Account) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
		
		if (acc == null) {
			return false;
		}
		
		return acc.isAdmin();
	}

}
