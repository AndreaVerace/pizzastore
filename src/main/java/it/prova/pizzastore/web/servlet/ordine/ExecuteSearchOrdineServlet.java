package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSearchOrdineServlet
 */
@WebServlet("/ExecuteSearchOrdineServlet")
public class ExecuteSearchOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceParam = request.getParameter("codice");
		String dataOrdineParam = request.getParameter("dataOrdine");
		String closedParam = request.getParameter("closed");
	
		
		Ordine example = new Ordine(codiceParam);
		example.setDataOrdine(UtilityForm.parseDateArrivoFromString(dataOrdineParam));
		example.setClosed(Boolean.parseBoolean(closedParam));
		
		
		
		try {
			request.setAttribute("ordini_list_attribute", MyServiceFactory.getOrdineServiceInstance()
					.findByExample(example));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/searchOrdine.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("pizzaiolo/listOrdine.jsp").forward(request, response);
	}

}
