package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.util.DateUtil;

@WebServlet("/cliente")
public class AdicionaClienteServlet extends HttpServlet {

	private static final long serialVersionUID = -7974124277262901066L;

	private ClienteDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = new ClienteDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// pegando os parâmetros
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String endereco =req.getParameter("endereco");
		String dataEmTexto = req.getParameter("dataNascimento");
		Calendar dataNascimento = null;

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.print("Erro ao converter a data!");
			return;
		}

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setEndereco(endereco);
		cliente.setDataNascimento(dataNascimento);
		dao.adicionar(cliente);

		StringBuilder resposta = new StringBuilder();
		resposta
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Cliente %s cadastrado com sucesso!</h3>")
			.append("		<a href=\"adiciona-cliente.html\">Novo Cliente</a>")
			.append("		&nbsp;")
			.append("		<a href=\"cliente\">Voltar</a>")
			.append("	</body>")
			.append("</html>");
		out.println(String.format(resposta.toString(), cliente.getNome()));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.addHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		List<Cliente> clientes = new ArrayList<>();
		clientes.addAll(dao.listar());

		out.println("<html>");
		out.println("	<body>");
		out.println("		<h3>Clientes</h3>");
		out.println("		<h5>" + clientes.size() + " registro(s)</h5>");
		out.println("		<p>");
		out.println("			<a href=\"adiciona-cliente.html\">Novo Cliente</a>");
		out.println("		</p>");
		out.println("		<table border=\"1\" width=\"720\">");
		out.println("			<thead>");
		out.println("				<tr>");
		out.println("					<th width=\"100\">Código</th>");
		out.println("					<th>Nome</th>");
		out.println("					<th>E-mail</th>");
		out.println("					<th>Endereço</th>");
		out.println("					<th>Data de Nascimento</th>");
		out.println("				</tr>");
		out.println("			</thead>");
		out.println("			<tbody>");

		clientes.forEach(cliente -> {
			out.println("			<tr>");
			out.println("				<td width=\"100\">" + cliente.getId() + "</td>");
			out.println("				<td>" + cliente.getNome() + "</td>");
			out.println("				<td>" + cliente.getEmail() + "</td>");
			out.println("				<td>" + cliente.getEndereco() + "</td>");
			out.println("				<td>" + DateUtil.parseToString(cliente.getDataNascimento(), "dd/MM/yyyy") + "</td>");
			out.println("			<tr>");
		});

		out.println("			</tbody>");
		out.println("		</table>");
		out.println("	</body");
		out.println("</html>");
	}
}
