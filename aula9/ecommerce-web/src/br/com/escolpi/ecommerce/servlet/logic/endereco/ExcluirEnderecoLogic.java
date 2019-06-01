package br.com.escolpi.ecommerce.servlet.logic.endereco;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirEnderecoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");

		EnderecoDao dao = new EnderecoDao();
		Long id = Long.valueOf(req.getParameter("id"));
		dao.remover(id);
		
		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Endereço excluído com sucesso!"));

		return "mvc?logica=ListarEndereco&clienteId=" + req.getParameter("clienteId");
	}

}
