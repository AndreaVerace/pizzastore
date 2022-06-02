package it.prova.pizzastore.web.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		String destinazione = null;

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance()
					.accedi(loginInput, passwordInput);
			
			if(utenteInstance == null) {
				request.setAttribute("errorMessage", "Utente non trovato.");
				destinazione = "login.jsp";
			} else {
				request.getSession().setAttribute("userInfo", utenteInstance);

				if (utenteInstance.getUsername().equalsIgnoreCase("admin") && utenteInstance.getPassword().equalsIgnoreCase("admin")) {
					destinazione = "admin/homePageAdmin.jsp";
				}

				if (loginInput.equalsIgnoreCase("pizzaiolo") && passwordInput.equalsIgnoreCase("pizzaiolo")) {
					destinazione = "pizzaiolo/homePagePizzaiolo.jsp";
				}

				if (utenteInstance.getUsername().equalsIgnoreCase("fattorino")
						&& utenteInstance.getPassword().equalsIgnoreCase("fattorino")) {
					destinazione = "fattorino/homePageFattorino.jsp";
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			destinazione = "login.jsp";
			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
		}

		request.getRequestDispatcher(destinazione).forward(request, response);
	}

}
