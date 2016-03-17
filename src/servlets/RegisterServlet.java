package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.account.User;
import exceptions.InvalidArgumentValueException;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({"/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/register.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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
			
			User user = new User(0, username, password, email, firstName, lastName, address, phoneNumber);
			user.register();
			response.sendRedirect("./");
		} catch (InvalidArgumentValueException e) {
			e.getMessage();
		}
	}

}
