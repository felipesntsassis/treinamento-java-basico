package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.Estados;
import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.servlet.delegate.ListarCliente;
import br.com.escolpi.ecommerce.servlet.delegate.SalvarCliente;
import br.com.escolpi.ecommerce.util.NumberUtil;
import br.com.escolpi.ecommerce.util.StringUtil;

/**
 * Servlet implementation class EcommerceServlet
 */
@WebServlet("/sistema")
public class EcommerceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String acao = req.getParameter("logica");
		String acaoFeedback = "cadastrado";
		ClienteDao clienteDao = new ClienteDao();
		
		String nomeDaClasse = "br.com.escolpi.ecommerce.servlet.delegate." + acao;

		try {
			Class classe = Class.forName(nomeDaClasse);
			Object objeto = classe.newInstance();
			
			if (nomeDaClasse.equals("br.com.escolpi.ecommerce.servlet.delegate.SalvarCliente")) {
				((SalvarCliente) objeto).executa(req, resp);
			} else if (nomeDaClasse.equals("br.com.escolpi.ecommerce.servlet.delegate.ListarCliente")) {
				((ListarCliente) objeto).executa(req, resp);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			System.out.println("Erro ao carregar sua lógica de negócio...");
			e1.printStackTrace();
		}
		
		/*
		if (acao.equals("SalvarCliente")) {
			Cliente cliente = new Cliente();

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
		} else if (acao.equals("ListarCliente")) {
			List<Cliente> clientes = clienteDao.listar();
			req.setAttribute("clientes", clientes);
			RequestDispatcher rd = req.getRequestDispatcher("/admin/cliente/lista.jsp");
			rd.forward(req, resp);
		} else if (acao.contentEquals("ExcluirCliente")) {
			if (req.getParameter("id") == null)
				throw new IllegalArgumentException("Parâmetro ID é obrigatório");

			Long clienteId = Long.valueOf(req.getParameter("id"));

			EnderecoDao enderecoDao = new EnderecoDao();
			Set<Endereco> enderecos = enderecoDao.listarPorClienteId(clienteId);
			enderecos.forEach(endereco -> enderecoDao.remover(endereco.getId()));
			clienteDao.remover(clienteId);
			feedback("excluído", req, resp);
		} else if (acao.contentEquals("EditarCliente")) {
			Cliente cliente = new Cliente();

			if (!StringUtil.isBlank(req.getParameter("id")))
				cliente = clienteDao.obter(Long.valueOf(req.getParameter("id")));

			req.setAttribute("cliente", cliente);
			RequestDispatcher rd = req.getRequestDispatcher("/admin/cliente/editar.jsp");
			rd.forward(req, resp);
		}
		*/
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
