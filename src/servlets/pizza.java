package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PizzaDb;
import pizzeria.menu.Ingredient;
import pizzeria.menu.Pizza;

/**
 * Servlet implementation class pizza
 */
@WebServlet({ "/pizza", "/menu" })
public class pizza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE HTML >");
		pw.println("<title>Pizza-Bug | Пица Бъг</title>");
		pw.println("<head>");
		pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		pw.println("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\" />");
		pw.println("<link href=\"bootstrap-3.3.6-dist/css/bootstrap.css\" "
				+ "rel=\"stylesheet\" type=\"text/css\" media=\"all\" />");
		pw.println("<script src=\"js/jquery-2.2.0.min.js\"></script>");
		pw.println("<script src=\"js/custom.js\"></script>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div id=\"left_side_content\">");
		pw.println("<div id=\"login\" class=\"login\">");
		pw.println("<form name=\"login\">");
		pw.println(
				"<label for=\"email\">E-mail:</label><input id=\"email\" type=\"text\" placeholder=\"E-mail\" value=\"\">"
						+ "<label for=\"password\">Парола:</label><input id=\"password\" type=\"password\" placeholder=\"Password\" value=\"\"><div>	<button class=\"btn btn-success\">Вход</button>"
						+ "<a class=\"btn btn-danger\" href=\"register.html\">Регистрация</a></div>");
		pw.println("</form></div></div>");
		pw.println("<div class=\"wrap\">");
		pw.println("<div class=\"wrapper\">");
		pw.println("<div class=\"header\"><div class=\"header_top\"><div class=\"logo\">"
				+ "<a href=\"index.html\"><img src=\"images/pizza/logo1.png\" alt=\"\" /></a>"
				+ "</div><div class=\"clear\"></div></div>");
		pw.println("<div class=\"header_bottom\"><div class=\"menu\"><ul>");
		pw.println("<li><a href=\"index.html\"><img src=\"images/list_img.png\" alt=\"\" />Начало</a>"
				+ "</li><li><a href=\"menu.html\"><img src=\"images/list_img.png\" alt=\"\" />Меню</a></li>"
				+ "<li><a href=\"about.html\"><img src=\"images/list_img.png\" alt=\"\" />За нас</a></li>"
				+ "<li><a href=\"shops.html\"><img src=\"images/list_img.png\" alt=\"\" />Магазини</a></li>");
		pw.println("</ul>" + "</div>" + "</div>" + "</div>");
		pw.println("<div class=\"main\"><div class=\"main_top\"><p>"
				+ "<button type=\"button\" class=\"btn btn btn-warning\">Пица</button>"
				+ "<button type=\"button\" class=\"btn btn btn-warning\">Салати</button>"
				+ "<button type=\"button\" class=\"btn btn btn-warning\">Напитки</button>"
				+ "</p><div class=\"clear\"></div></div>");
		pw.println("<div class=\"about_data\">");
		PizzaDb pizzaDao = new PizzaDb();
		List<Pizza> list = pizzaDao.getAllPizza();
		for (Pizza pizza : list) {
			List<Ingredient> ingredients = pizzaDao.getAllPizzaIngredients(pizza);
			String ing = "";
			for (Ingredient ingredient : ingredients) {
				ing += ingredient.getName();
				ing += ", ";
			}
			pw.println("<div class=\"pizza_details\">"
					+ "<img src=\"images\\pizza\\Pepperoni_Lovers_Pizza.png\" alt=\"\" />"
					+ "<div class=\"desc\">	<ul>");
			pw.println("<li><span>" + "Име" + "</span> : " + pizza.getName() + "</li>");
			pw.println("<li><span>Цена</span> : " + pizza.getPrice() + "</li>");
			pw.println("<li><span>Съставки</span> : " + ing + "</li>");
			pw.println("<li><button type=\"button\" class=\"btn btn-sm btn-success\">Добави</button>"
					+ "</li></ul></div><div class=\"clear\"></div></div>");
		}
		pw.println("<div class=\"clear\"></div></div></div><div class=\"footer\"><div class=\"copy_right\"><p>2016 rights Reserved"
				+ "</p></div></div></div>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
	}

}
