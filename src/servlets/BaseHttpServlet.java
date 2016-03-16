package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet(description = "Base (abstract) Servlet that will have common methods")
public abstract class BaseHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BaseHttpServlet() {
        super();
    }

	protected boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if (session == null || session.isNew()) {
			return false;
		}
		
		if (session.getAttribute("loggedUser") != null &&
				!session.getAttribute("loggedUser").equals("")) {
			return true;
		}
		
		return false;
	}

}
