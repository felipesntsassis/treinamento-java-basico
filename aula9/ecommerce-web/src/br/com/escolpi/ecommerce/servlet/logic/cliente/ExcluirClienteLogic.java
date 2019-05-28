package br.com.escolpi.ecommerce.servlet.logic.cliente;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirClienteLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");

		Long clienteId = Long.valueOf(req.getParameter("id"));

		ClienteDao clienteDao = new ClienteDao();
		EnderecoDao enderecoDao = new EnderecoDao();
		Set<Endereco> enderecos = enderecoDao.listarPorClienteId(clienteId);
		enderecos.forEach(endereco -> enderecoDao.remover(endereco.getId()));
		clienteDao.remover(clienteId);
		
		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Cliente excluído com sucesso!"));

		return "mvc?logica=ListarCliente";
	}

}
