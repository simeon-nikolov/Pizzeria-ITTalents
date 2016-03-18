package servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;
import servlets.BaseHttpServlet;
import database.IngredientDb;
import database.PizzaDb;
import exceptions.InvalidArgumentValueException;

@WebServlet("/admin/addPizza")
public class AddPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			IngredientDb ingDao = new IngredientDb();
			List<Ingredient> ingredients = ingDao.getAllIngredients();
			request.setAttribute("ingredients", ingredients);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../jsp/admin/add_pizza.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			String imageUrl = "resources/images/pizza/Primo_Meat.png";
			int grammage = Integer.parseInt(request.getParameter("grammage"));
			int size = Integer.parseInt(request.getParameter("size"));
			
			try {
				Pizza pizza = new Pizza(0, name, price, imageUrl, grammage, size);
				PizzaDb pizzaDao = new PizzaDb();
				pizzaDao.addPizza(pizza);
			} catch (InvalidArgumentValueException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("../admin/products");
			
		} else {
			response.sendRedirect("../");
		}
	}

}
