package servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizzeria.Pizzeria;
import pizzeria.menu.IProduct;
import servlets.BaseHttpServlet;

@WebServlet("/admin/products")
public class ProductsServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (BaseHttpServlet.isAdmin(request)) {
			Pizzeria pizzeria = new Pizzeria();
			List<IProduct> products = pizzeria.showMenu();
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/products.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
