package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopsServlet
 */
@WebServlet("/Shops")
public class ShopsServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("auth", this);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/shops.jsp");
		dispatcher.forward(request, response);
	}

}
