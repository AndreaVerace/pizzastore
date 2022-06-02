package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;


@WebServlet("/ExecuteInsertClienteServlet")
public class ExecuteInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		String attivoParam = request.getParameter("attivo");
		
		Cliente clienteInstance = UtilityForm
				.createClienteFromParams(nomeParam, cognomeParam, indirizzoParam, attivoParam);
		
		if (!UtilityForm.validateClienteBean(clienteInstance)) {
			request.setAttribute("insert_regista_attr", clienteInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/admin/insert.jsp").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getClienteServiceInstance().inserisciNuovo(clienteInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/admin/insert.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListClienteServlet?operationResult=SUCCESS");
	}

}
