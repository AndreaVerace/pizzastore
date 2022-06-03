package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareSearchOrdineServlet
 */
@WebServlet("/PrepareSearchOrdineServlet")
public class PrepareSearchOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pizzaiolo/searchOrdine.jsp").forward(request, response);
		
		
		
		
		
	}



}
