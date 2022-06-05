package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdateClienteServlet
 */
@WebServlet(name = "/admin/ExecuteUpdateClienteServlet",urlPatterns = {"/admin/ExecuteUpdateClienteServlet"})
public class ExecuteUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idClienteParam = request.getParameter("idCliente");
		
		if (!NumberUtils.isCreatable(idClienteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		}
		
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		String attivoParam = request.getParameter("attivo");
		
		Cliente clienteInstance = UtilityForm
				.createClienteFromParams(nomeParam, cognomeParam, indirizzoParam, attivoParam);
		
		clienteInstance.setId(Long.parseLong(idClienteParam));
		
		if (!UtilityForm.validateClienteBean(clienteInstance)) {
			request.setAttribute("insert_regista_attr", clienteInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/admin/insert.jsp").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getClienteServiceInstance().aggiorna(clienteInstance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListClienteServlet?operationResult=SUCCESS");
	}

}
