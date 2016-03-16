package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/home")
public class IndexServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("auth", this);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/home.jsp");
		dispatcher.forward(request, response);
	}

}
