package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;


/**
 * Servlet implementation class PrepareInsertPizzaServlet
 */
@WebServlet("/PrepareInsertPizzaServlet")
public class PrepareInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("delete_pizza_attr", new Pizza());
		request.getRequestDispatcher("/pizzaiolo/insert.jsp").forward(request, response);
	}

	

}
