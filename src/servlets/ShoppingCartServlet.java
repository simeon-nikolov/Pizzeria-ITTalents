package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.account.User;
import pizzeria.menu.Pizza;
import database.PizzaDb;
import exceptions.InvalidArgumentValueException;

/**
 * Servlet implementation class ProductToCart
 */
@WebServlet("/cart")
public class ShoppingCartServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./menu");
			return;
		}
		
		PizzaDb pizzaDao = new PizzaDb();
		int pizzaId = Integer.parseInt(request.getParameter("id"));
		Pizza pizza = pizzaDao.getPizzaById(pizzaId);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(super.LOGGED_USER_ATTRIBUTE_NAME);
		
		try {
			user.getShoppingCart().addProduct(pizza, 1);
		} catch (InvalidArgumentValueException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./menu");
	}

}
