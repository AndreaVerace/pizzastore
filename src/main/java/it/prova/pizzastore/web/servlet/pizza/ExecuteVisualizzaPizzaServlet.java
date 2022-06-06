package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteVisualizzaPizzaServlet
 */
@WebServlet(name = "/pizzaiolo/ExecuteVisualizzaPizzaServlet",urlPatterns = {"/pizzaiolo/ExecuteVisualizzaPizzaServlet"})
public class ExecuteVisualizzaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPizzaParam = request.getParameter("idPizza");
		
		if (!NumberUtils.isCreatable(idPizzaParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		}
		
		try {
			Pizza pizzaInstance = MyServiceFactory.getPizzaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idPizzaParam));
			
			request.setAttribute("show_pizza_attr", pizzaInstance);
			
			if(pizzaInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListPizzaServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/pizzaiolo/show.jsp").forward(request, response);
	}

	

}
