package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteVisualizzaFattorinoServlet
 */
@WebServlet(name = "/fattorino/ExecuteVisualizzaFattorinoServlet",urlPatterns = {"/fattorino/ExecuteVisualizzaFattorinoServlet"})
public class ExecuteVisualizzaFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");
		
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("listOrdine.jsp").forward(request, response);
			return;
		}
		
		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idOrdineParam));
			
			request.setAttribute("show_ordine_attr", ordineInstance);
			
			
			if(ordineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListFattorinoServlet?operationResult=NOT_FOUND").forward(request,
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
			request.getRequestDispatcher("listOrdine.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("showOrdine.jsp").forward(request, response);
	}

	

}
