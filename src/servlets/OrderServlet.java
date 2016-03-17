package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ShopDb;
import pizzeria.account.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!super.isAuthenticated(request)) {
			response.sendRedirect("./");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/order.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseHttpServlet.LOGGED_USER_ATTRIBUTE_NAME);
		ShopDb shopDAO = new ShopDb(); 
		user.makeOrder(shopDAO.getShopById(2));
//		request.setAttribute("price", arg1);
		response.sendRedirect("./menu");
		
		
	}

}
