package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.menu.Pizza;
import database.PizzaDb;

/**
 * Servlet implementation class ProductToCart
 */
@WebServlet("/ProductToCart")
public class ProductToCart extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductToCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./menu");
			return;
		}
		
		PizzaDb pizzaDAO = new PizzaDb();
		String productId = request.getParameter("name");
		// kak da vzema samo chisloto -id
		Pizza p = pizzaDAO.getPizzaById(Integer.parseInt(productId.substring(0, 2)));
		HttpSession session = request.getSession();
		session.setAttribute(p.getName(), p);
		response.sendRedirect("./menu");
	}

}
