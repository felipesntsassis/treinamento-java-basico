package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;

@WebServlet("/admin/cliente")
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
		Cliente cliente = new Cliente();

		if (req.getParameter("id") != null)
			cliente.setId(Long.valueOf(req.getParameter("id")));

		cliente.setNome(req.getParameter("nome"));
		cliente.setEmail(req.getParameter("email"));
		cliente.setEndereco(req.getParameter("endereco"));
		Calendar dataNascimento = Calendar.getInstance();

		try {
			dataNascimento.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(
					req.getParameter("dataNascimento")));
		} catch (ParseException e) {
			System.out.println("Erro ao converter a data de nascimento!");
		}

		cliente.setDataNascimento(dataNascimento);
		String acao = "cadastrado";

		if (cliente.getId() != null && cliente.getId() > 0) {
			dao.alterar(cliente);
			acao = "alterado";
		} else {
			dao.adicionar(cliente);
		}

		feedback(acao, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		
		dao.remover(Long.valueOf(req.getParameter("id")));
		feedback("excluído", resp);
	}

	private void feedback(String acao, HttpServletResponse resp) throws IOException {
		resp.addHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		StringBuilder resposta = new StringBuilder();
		resposta
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Cliente %s com sucesso!</h3>")
			.append("		<a href=\"/ecommerce-web/admin/cliente/editar-taglib.jsp\">Novo Cliente</a>")
			.append("		&nbsp;")
			.append("		<a href=\"/ecommerce-web/admin/cliente/lista-taglib.jsp\">Voltar</a>")
			.append("	</body>")
			.append("</html>");
		out.println(String.format(resposta.toString(), acao));
	}

}
