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
		String pizza = request.getParameter("pizza.id");
		
		Ordine ordineInstance = UtilityForm
				.createOrdineFromParams(codiceParam, dataOrdineParam, closedParam, utenteParam, clienteParam);
		
			
			Object p = pizza;
			Set<Pizza> setPizza = new HashSet<>();
			setPizza.add((Pizza) p);
			
			ordineInstance.setPizze(setPizza);
		
		
		
		
		try {
			// se la validazione non risulta ok
			if (!UtilityForm.validateOrdineBean(ordineInstance)) {
				request.setAttribute("insert_ordine_attr", ordineInstance);
				
				// questo mi serve per la select di registi in pagina
				request.setAttribute("utenti_list_attribute",
						MyServiceFactory.getOrdineServiceInstance().listAllElements());
				
				request.setAttribute("pizze_list_attribute", ordineInstance.getPizze());
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizzaiolo/insertOrdine.jsp").forward(request, response);
				return;
			}

			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			// occupiamoci delle operazioni di business
			MyServiceFactory.getOrdineServiceInstance().inserisciNuovo(ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/insertOrdine.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
	}

}
