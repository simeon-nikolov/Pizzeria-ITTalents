package servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
import servlets.BaseHttpServlet;
import database.UserDb;

@WebServlet("/admin/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			UserDb userDao = new UserDb();
			List<User> users = new ArrayList<User>();
			String username = request.getParameter("username");
			
			if (username != null) {
				User user = userDao.getUserByUsername(username);
				users.add(user);
			} else {
				users = userDao.getAllUsers();
			}
			
			request.setAttribute("users", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/users.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}
}
