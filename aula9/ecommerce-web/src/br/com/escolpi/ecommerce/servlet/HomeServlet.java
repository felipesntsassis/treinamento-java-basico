package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	name = "homeServlet",
	urlPatterns = { "/admin/home", "/inicio" },
	initParams = {
			@WebInitParam(name = "param1", value = "valor 1"),
			@WebInitParam(name = "param2", value = "valor 2")
	}
)
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 3707525240301876199L;

	private String parametro1;
	private String parametro2;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		parametro1 = config.getInitParameter("param1");
		parametro2 = config.getInitParameter("param2");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Primeira Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Oi mundo Servlet!</h1>");
		out.println("<p>Valor do Parâmetro 1: " + parametro1 + "</p>");
		out.println("<p>Valor do Parâmetro 2: " + parametro2 + "</p>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
