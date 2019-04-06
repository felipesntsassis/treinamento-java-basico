package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;

@WebServlet("/vendedor")
public class AdicionaVendedorServlet extends HttpServlet {

	private static final long serialVersionUID = 4355861423557205824L;

	private VendedorDao dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new VendedorDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		Vendedor vendedor = new Vendedor();
		vendedor.setNome(req.getParameter("nome"));
		vendedor.setEmail(req.getParameter("email"));
		vendedor.setDepartamento(req.getParameter("departamento"));

		try {
			Double percentual = Double.valueOf(req.getParameter("percComissao"));
			vendedor.setPercentualComissao(percentual);
		} catch (NumberFormatException e) {
			out.println("Erro ao converter double!");
			return;
		}

		dao.adicionar(vendedor);

		StringBuilder resposta = new StringBuilder();
		resposta
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Vendedor %s cadastrado com sucesso!</h3>")
			.append("		<a href=\"adiciona-vendedor.html\">Novo Vendedor</a>")
			.append("		&nbsp;")
			.append("		<a href=\"vendedor\">Voltar</a>")
			.append("	</body>")
			.append("</html>");
		out.println(String.format(resposta.toString(), vendedor.getNome()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		List<Vendedor> vendedores = new ArrayList<>();
		vendedores.addAll(dao.listar());

		out.println("<html>");
		out.println("	<body>");
		out.println("		<h3>Vendedores</h3>");
		out.println("		<h5>" + vendedores.size() + " registro(s)</h5>");
		out.println("		<p>");
		out.println("			<a href=\"adiciona-vendedor.html\">Novo Vendedor</a>");
		out.println("		</p>");
		out.println("		<table border=\"1\" width=\"720\">");
		out.println("			<thead>");
		out.println("				<tr>");
		out.println("					<th width=\"100\">Código</th>");
		out.println("					<th>Nome</th>");
		out.println("					<th>E-mail</th>");
		out.println("					<th>Departamento</th>");
		out.println("					<th>(%) de Comissão</th>");
		out.println("				</tr>");
		out.println("			</thead>");
		out.println("			<tbody>");

		vendedores.forEach(vendedor -> {
			out.println("			<tr>");
			out.println("				<td width=\"100\">" + vendedor.getId() + "</td>");
			out.println("				<td>" + vendedor.getNome() + "</td>");
			out.println("				<td>" + vendedor.getEmail() + "</td>");
			out.println("				<td>" + vendedor.getDepartamento() + "</td>");

			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(2);

			out.println("				<td>" + nf.format(vendedor.getPercentualComissao()) + "</td>");
			out.println("			<tr>");
		});

		out.println("			</tbody>");
		out.println("		</table>");
		out.println("	</body");
		out.println("</html>");
	}
}
