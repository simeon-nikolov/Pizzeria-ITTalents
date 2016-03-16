package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDb;
import exceptions.InvalidArgumentValueException;
import pizzeria.account.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({ "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			String phoneNumber = request.getParameter("phoneto");
			validateString(firstName);
			validateString(lastName);
			validateString(userName);
			validateString(email);
			validateString(password);
			validateString(secondPassword);
			validateString(address);
			validateString(phoneNumber);
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
			userDAO.addUser(new User(listUsers.size() + 1, userName, password, email, firstName, lastName, address,
					phoneNumber));
			response.sendRedirect("./home");
		} catch (InvalidArgumentValueException e) {
			e.getMessage();
		}
	}

	private void validateString(String str) throws InvalidArgumentValueException {
		if (str == null || str.trim().equals("")) {
			throw new InvalidArgumentValueException("Nekorektna vyvedena danna" + str);
		}
	}

}
