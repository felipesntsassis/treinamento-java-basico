package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;

@WebServlet(name="categoriaServlet", urlPatterns = "/categoria")
public class AdicionaCategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = -6107830990774324951L;

	private CategoriaDao dao = new CategoriaDao();

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new CategoriaDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getHeader("Content-type");
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		Categoria categoria = new Categoria();
		categoria.setDescricao(req.getParameter("descricao"));
		
		dao.adicionar(categoria);
		
		StringBuilder resposta = new StringBuilder();
		resposta
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Categoria %s cadastrado com sucesso!</h3>")
			.append("		<a href=\"adiciona-categoria.html\">Nova Categoria</a>")
			.append("		&nbsp;")
			.append("		<a href=\"categoria\">Voltar</a>")
			.append("	</body>")
			.append("</html>");
		out.println(String.format(resposta.toString(), categoria.getDescricao()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		List<Categoria> categorias = new ArrayList<>();
		categorias.addAll(dao.listar());
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		<h3>Categorias</h3>");
		out.println("		<h5>" + categorias.size() + " registro(s)</h5>");
		out.println("		<p>");
		out.println("			<a href=\"adiciona-categoria.html\">Nova Categoria</a>");
		out.println("		</p>");
		out.println("		<table border=\"1\" width=\"720\">");
		out.println("			<thead>");
		out.println("				<tr>");
		out.println("					<th width=\"100\">Código</th>");
		out.println("					<th>Descrição</th>");
		out.println("				</tr>");
		out.println("			</thead>");
		out.println("			<tbody>");

		categorias.forEach(categoria -> {
			out.println("			<tr>");
			out.println("			<td width=\"100\">" + categoria.getId() + "</td>");
			out.println("			<td>" + categoria.getDescricao() + "</td>");
			out.println("			<tr>");
		});

		out.println("			</tbody>");
		out.println("		</table>");
		out.println("	</body");
		out.println("</html>");
	}

}
