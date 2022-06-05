package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;


/**
 * Servlet implementation class ExecuteInsertOrdineServlet
 */
@WebServlet("/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceParam = request.getParameter("codice");
		String dataOrdineParam = request.getParameter("dataOrdine");
		String closedParam = request.getParameter("closed");
		String utenteParam = request.getParameter("utente.id");
		String clienteParam = request.getParameter("cliente.id");
		String[] pizzeParam = request.getParameterValues("pizza.id");
		
		
		
		Ordine ordineInstance = UtilityForm
				.createOrdineFromParams(codiceParam, dataOrdineParam, closedParam, utenteParam, clienteParam);
		/*Set<Pizza> pizzeOrdine = new HashSet<Pizza>();
		for(String pizza : pizzeParam) {
			Pizza p = new Pizza(Long.parseLong(pizza));
			pizzeOrdine.add(p);
		}
		ordineInstance.setPizze(pizzeOrdine);*/
		
		
		try {
			
			if (!UtilityForm.validateOrdineBean(ordineInstance)) {
				request.setAttribute("insert_ordine_attr", ordineInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizzaiolo/insertOrdine.jsp").forward(request, response);
				return;
			}
			
			ordineInstance.costoTotaleOrdini();
			
			MyServiceFactory.getOrdineServiceInstance().inserisciNuovo(ordineInstance);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
	}

}
