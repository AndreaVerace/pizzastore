package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exceptions.ElementNotFoundException;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteChiudiOrdineFattorinoServlet
 */
@WebServlet("/ExecuteChiudiOrdineFattorinoServlet")
public class ExecuteChiudiOrdineFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");
		
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("fattorino/listOrdine.jsp").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getOrdineServiceInstance()
				.chiudiOrdine(Long.parseLong(idOrdineParam));
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteChiudiOrdineFattorinoServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("fattorino/listOrdine.jsp").forward(request, response);
			return;
		}
		
		
		
		response.sendRedirect("ExecuteListFattorinoServlet?operationResult=SUCCESS");
	}

}
