package servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
import servlets.BaseHttpServlet;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			UserDb userDao = new UserDb();
			int userId = Integer.parseInt(request.getParameter("id"));
			User user = userDao.getUserById(userId);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/edit_user.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			UserDb userDao = new UserDb();
			int userId = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			User user = userDao.getUserById(userId);
			User newUserInfo = new User();
			
			try {
				newUserInfo.setUsername(username);
				newUserInfo.setPassword(user.getPassword());
				newUserInfo.setFirstName(firstName);
				newUserInfo.setLastName(lastName);
				newUserInfo.setEmail(email);
				newUserInfo.setPhoneNumber(phone);
				newUserInfo.setAddress(address);
			} catch (InvalidArgumentValueException e) {
				e.printStackTrace();
			}
			
			user.updateUser(newUserInfo);
			user = userDao.getUserById(userId);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/edit_user.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}

}
