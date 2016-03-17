package servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
import servlets.BaseHttpServlet;
import database.UserDb;

@WebServlet("/admin/user")
public class ViewUserServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			int userId = Integer.parseInt(request.getParameter("id"));
			UserDb userDao = new UserDb();
			User user = userDao.getUserById(userId);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/view_user.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}
}
