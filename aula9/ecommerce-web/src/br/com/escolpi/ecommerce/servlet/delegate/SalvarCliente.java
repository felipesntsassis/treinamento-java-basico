package br.com.escolpi.ecommerce.servlet.delegate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.Estados;
import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.util.NumberUtil;
import br.com.escolpi.ecommerce.util.StringUtil;

public class SalvarCliente {

	public void executa(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		Cliente cliente = new Cliente();
		ClienteDao clienteDao = new ClienteDao();
		String acaoFeedback = "cadastrado";

		if (req.getParameter("id") != null)
			cliente.setId(Long.valueOf(req.getParameter("id")));

		cliente.setNome(req.getParameter("nome"));
		cliente.setEmail(req.getParameter("email"));
		Calendar dataNascimento = Calendar.getInstance();

		try {
			dataNascimento.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(
					req.getParameter("dataNascimento")));
		} catch (ParseException e) {
			System.out.println("Erro ao converter a data de nascimento!");
		}

		cliente.setDataNascimento(dataNascimento);

		if (cliente.getId() != null && cliente.getId() > 0) {
			clienteDao.alterar(cliente);
			acaoFeedback = "alterado";
		} else {
			clienteDao.adicionar(cliente);
		}

		salvarEnderecoPrincipal(req, cliente);
		feedback(acaoFeedback, req, resp);
	}

	private void feedback(String acao, HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cliente/feedback.jsp");
		req.setAttribute("acao", acao);
		dispatcher.forward(req, resp);
	}

	private void salvarEnderecoPrincipal(HttpServletRequest req, Cliente cliente) {
		Endereco endereco = new Endereco();
		EnderecoDao enderecoDao = new EnderecoDao();

		if (!StringUtil.isBlank(req.getParameter("enderecoId")))
			endereco.setId(Long.valueOf(req.getParameter("enderecoId")));

		endereco.setClienteId(cliente.getId());
		endereco.setCep(StringUtil.removerCaracteresEspeciais(req.getParameter("cep")));
		endereco.setLogradouro(req.getParameter("endereco"));
		endereco.setNumero(req.getParameter("numero"));
		endereco.setBairro(req.getParameter("bairro"));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setEstado(Estados.obterPorSigla(req.getParameter("estado")));
		endereco.setMunicipio(req.getParameter("municipio"));
		endereco.setEnderecoPrincipal(true);

		if (!NumberUtil.isNullOrZero(endereco.getId())) {
			enderecoDao .alterar(endereco);
		} else {
			enderecoDao.adicionar(endereco);
			
		}
	}
}
