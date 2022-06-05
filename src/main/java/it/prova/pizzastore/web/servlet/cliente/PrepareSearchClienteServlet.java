package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrepareSearchClienteServlet
 */
@WebServlet(name = "/admin/PrepareSearchClienteServlet",urlPatterns = {"/admin/PrepareSearchClienteServlet"})
public class PrepareSearchClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}



}
