package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.account.User;
import database.UserDb;
import exceptions.InvalidArgumentValueException;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/edit")
public class EditProfileServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("username");
			String email = request.getParameter("e-mail");
			String password = request.getParameter("first_password");
			String secondPassword = request.getParameter("second_password");
			String address = request.getParameter("addres");
			String phoneNumber = request.getParameter("phone");
			
			if (!password.equals(secondPassword)) {
				throw new InvalidArgumentValueException("Vyvedenite paroli sa razlichni!!");
			}
			UserDb userDAO = new UserDb();
			List<User> listUsers = userDAO.getAllUsers();
			for (User user : listUsers) {
				if (user.getUsername().equals(userName)) {
					throw new InvalidArgumentValueException("Username syshtestuva");
				}
			}
			HttpSession session = request.getSession();
			userDAO.editUser(((User) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME)).getId(),
					(new User(listUsers.size() + 1, userName, password, email, firstName, lastName, address,
							phoneNumber)));
			response.sendRedirect("./");
		} catch (InvalidArgumentValueException e) {
			e.getMessage();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/edit.jsp");
		dispatcher.forward(request, response);
	}

}
