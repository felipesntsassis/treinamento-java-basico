package br.com.escolpi.ecommerce.servlet.logic.endereco;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarEnderecoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long clienteId = Long.valueOf(req.getParameter("clienteId"));
		Endereco endereco = new Endereco();
		EnderecoDao dao = new EnderecoDao();
		endereco.setClienteId(clienteId);

		if (!StringUtil.isBlank(req.getParameter("id")))
			endereco = dao.obter(Long.valueOf(req.getParameter("id")));

		req.setAttribute("endereco", endereco);

		return "admin/cliente/endereco/editar.jsp";
	}

}
