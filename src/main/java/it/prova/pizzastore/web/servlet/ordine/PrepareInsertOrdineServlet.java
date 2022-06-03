package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;


/**
 * Servlet implementation class PrepareInsertOrdineServlet
 */
@WebServlet("/PrepareInsertOrdineServlet")
public class PrepareInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("insert_ordine_attr", new Ordine());
			// questo mi serve per la select di registi in pagina
			request.setAttribute("clienti_list_attribute",
					MyServiceFactory.getClienteServiceInstance().listAllElements());
			
			
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance()
					.findAllByRuolo(Ruolo.ROLE_FATTORINO));
			
			request.setAttribute("pizze_list_attribute", MyServiceFactory.getPizzaServiceInstance()
					.listAllElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("homePagePizzaiolo.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/pizzaiolo/insertOrdine.jsp").forward(request, response);
	}

	
}
