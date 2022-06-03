package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdateOrdineServlet
 */
@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");
		
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("listOrdine.jsp").forward(request, response);
			return;
		}
		
		String codiceParam = request.getParameter("codice");
		String dataOrdineParam = request.getParameter("dataOrdine");
		String closedParam = request.getParameter("closed");
		String utenteParam = request.getParameter("utente.id");
		String clienteParam = request.getParameter("cliente.id");
		
		Ordine ordineInstance = UtilityForm
				.createOrdineFromParams(codiceParam, dataOrdineParam, closedParam, utenteParam, clienteParam);
		
		ordineInstance.setId(Long.parseLong(idOrdineParam));
		
		if (!UtilityForm.validateOrdineBean(ordineInstance)) {
			request.setAttribute("insert_ordine_attr", ordineInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizzaiolo/editOrdine.jsp").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/editOrdine.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
	}

}
