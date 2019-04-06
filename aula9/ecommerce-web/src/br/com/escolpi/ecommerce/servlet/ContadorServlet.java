package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContadorServlet
 */
@WebServlet("/contador")
public class ContadorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private int contador = 0;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("ContadorServlet inicializado!");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("ContadorServlet destruído...");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		contador ++;

		out.println("<html>");
		out.println("<body>");
		out.println("Contador agora é: " + contador);
		out.println("</body>");
		out.println("</html>");
		
		if (contador == 20) {
			destroy();
		}
	}

}
