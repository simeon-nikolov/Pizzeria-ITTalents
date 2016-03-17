package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.Pizzeria;
import pizzeria.account.Account;
import exceptions.InvalidArgumentValueException;

@WebServlet("/login")
public class LoginServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (super.isAuthenticated(request)) {
			response.sendRedirect("./");
			return;
		}
		
		Pizzeria pizzeria = new Pizzeria();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if (pizzeria.login(username, password)) {
				HttpSession session = request.getSession(true);
				Account acc = pizzeria.getAccountByUsername(username);
				session.setAttribute(super.LOGGED_USER_ATTRIBUTE_NAME, acc);
			}
		} catch (InvalidArgumentValueException e) {
			// TO DO: add response message for user
		}
		
		response.sendRedirect("./");
	}

}
