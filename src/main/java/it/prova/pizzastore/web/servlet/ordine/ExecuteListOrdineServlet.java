package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListOrdineServlet
 */
@WebServlet(name = "/pizzaiolo/ExecuteListOrdineServlet",urlPatterns = {"/pizzaiolo/ExecuteListOrdineServlet"})
public class ExecuteListOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//se nell'url della request è presente SUCCESS significa che devo mandare un 
			//messaggio di avvenuta operazione in pagina
			String operationResult = request.getParameter("operationResult");
			if(StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
				request.setAttribute("successMessage", "Operazione effettuata con successo");
			
			request.setAttribute("ordini_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("homePagePizzaiolo.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("listOrdine.jsp").forward(request, response);
	}


}
