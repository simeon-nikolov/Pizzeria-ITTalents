package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
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
			if (!super.isAuthenticated(request)) {
				response.sendRedirect("./");
				return;
			}
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String username = request.getParameter("username");
			String email = request.getParameter("e-mail");
			String password = request.getParameter("first_password");
			String secondPassword = request.getParameter("second_password");
			String address = request.getParameter("addres");
			String phoneNumber = request.getParameter("phone");
			
			if (!password.equals(secondPassword)) {
				throw new InvalidArgumentValueException("Vyvedenite paroli sa razlichni!!");
			}
			
			User user = (User) request.getSession().getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
			User newUserInfo = new User(0, username, secondPassword, email, firstName, lastName, address, phoneNumber);
			user.updateUser(newUserInfo);
			
			response.sendRedirect("./profile");
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
