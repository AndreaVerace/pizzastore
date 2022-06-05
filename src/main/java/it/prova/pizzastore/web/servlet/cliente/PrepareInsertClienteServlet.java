package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;


@WebServlet(name = "/admin/PrepareInsertClienteServlet",urlPatterns = {"/admin/PrepareInsertClienteServlet"})
public class PrepareInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("insert_cliente_attr", new Cliente());
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}



}
