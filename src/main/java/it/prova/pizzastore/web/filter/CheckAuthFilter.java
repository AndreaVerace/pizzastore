package it.prova.pizzastore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;




@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/"};
	private static final String[] ADMIN_URLS = {"/admin/"};
	private static final String[] PIZZAIOLO_URLS = {"/pizzaiolo/"};
	private static final String[] FATTORINO_URLS = {"/fattorino/"};

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();
		
		//vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		//se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente)httpRequest.getSession().getAttribute("userInfo");
			//intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
			//controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if(isPathForOnlyAdmin(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("messaggio", "Non si ?? autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/homePageAdmin.jsp").forward(httpRequest, httpResponse);
				return;
			}
			
			if(isPathForOnlyPizzaiolo(pathAttuale) && !utenteInSession.isPizzaiolo()) {
				httpRequest.setAttribute("messaggio", "Non si ?? autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("pizzaiolo/homePagePizzaiolo.jsp").forward(httpRequest, httpResponse);
				return;
			}
			
			if(isPathForOnlyFattorino(pathAttuale) && !utenteInSession.isFattorino()) {
				httpRequest.setAttribute("messaggio", "Non si ?? autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("fattorino/homePageFattorino.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyAdmin(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyPizzaiolo(String requestPath) {
		for (String urlPatternItem : PIZZAIOLO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyFattorino(String requestPath) {
		for (String urlPatternItem : FATTORINO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	public void destroy() {
	}

}
