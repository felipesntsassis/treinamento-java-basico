package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String parametro = req.getParameter("logica");

		try {
			Class classe = Class.forName(getClassName(parametro));
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(req, resp);
			req.getRequestDispatcher(pagina).forward(req, resp);
		} catch (Exception e) {
			throw new ServletException("A lógica de negócios causou uma exceção", e);
		}
	}

	private String getClassName(String parametro) {
		String[] logica = parametro.split("(?=\\p{Upper})");
		return "br.com.escolpi.ecommerce.servlet.logic." 
				+ logica[1].toLowerCase() + "." + parametro + "Logic";
		
	}
}
