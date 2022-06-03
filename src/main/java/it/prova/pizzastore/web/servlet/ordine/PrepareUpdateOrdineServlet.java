package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateOrdineServlet
 */
@WebServlet("/PrepareUpdateOrdineServlet")
public class PrepareUpdateOrdineServlet extends HttpServlet {
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
			
			request.setAttribute("edit_ordine_attr", ordineInstance);
			
			request.setAttribute("clienti_list_attribute",
					MyServiceFactory.getClienteServiceInstance().listAllElements());
			
			
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance()
					.findAllByRuolo(Ruolo.ROLE_FATTORINO));
			
			if(ordineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListOrdineServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			/*request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("listOrdine.jsp").forward(request, response);
			return;*/
		}
		
		request.getRequestDispatcher("/pizzaiolo/editOrdine.jsp").forward(request, response);
	}

	

}
