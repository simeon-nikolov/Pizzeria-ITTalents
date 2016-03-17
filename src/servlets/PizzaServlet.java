package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pizzeria.account.Account;
import pizzeria.account.User;
import pizzeria.menu.IProduct;
import pizzeria.menu.Pizza;
import database.PizzaDb;

@WebServlet({ "/pizza", "/menu" })
public class PizzaServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PizzaDb pizzaDao = new PizzaDb();
		List<Pizza> list = pizzaDao.getAllPizza();
		
		Boolean isAuthenticated = super.isAuthenticated(request);
		request.setAttribute("pizza", list);
		request.setAttribute("auth", isAuthenticated);
		
		if (isAuthenticated) {
			HttpSession session = request.getSession();
			Account acc = (Account) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
			
			if (!acc.isAdmin()) {
				User user = (User) acc;
				List<IProduct> productsInShoppingCart = user.getShoppingCart().getProducts();
				request.setAttribute("cartProducts", productsInShoppingCart);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/pizza.jsp");
		dispatcher.forward(request, response);
	}
}
