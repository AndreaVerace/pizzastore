package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;


@WebServlet("/ExecuteSearchPizzaServlet")
public class ExecuteSearchPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzoBase");
		
		Pizza example = new Pizza(descrizioneParam,ingredientiParam);
		example.setPrezzoBase(Integer.parseInt(prezzoBaseParam));
		
		try {
			request.setAttribute("pizze_list_attribute", MyServiceFactory.getPizzaServiceInstance()
					.findByExample(example));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/search.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("pizzaiolo/list.jsp").forward(request, response);
	}

}
