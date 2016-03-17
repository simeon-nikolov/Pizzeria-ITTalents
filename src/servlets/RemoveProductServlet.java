package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.account.User;

/**
 * Servlet implementation class RemoveProductServlet
 */
@WebServlet("/remove")
public class RemoveProductServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./menu");
			return;
		}
		
		int productId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
		user.getShoppingCart().removeProduct(productId);
		response.sendRedirect("./menu");
	}

}
