package servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
import servlets.BaseHttpServlet;
import database.OrderDb;
import database.UserDb;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			UserDb userDao = new UserDb();
			int userId = Integer.parseInt(request.getParameter("id"));
			User user = userDao.getUserById(userId);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/delete_user.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			int userId = Integer.parseInt(request.getParameter("id"));
			OrderDb orderDao = new OrderDb();
			orderDao.deleteUserOrders(userId);
			UserDb userDao = new UserDb();
			userDao.removeUser(userId);
			response.sendRedirect("../admin/users");
		} else {
			response.sendRedirect("../");
		}
	}

}
