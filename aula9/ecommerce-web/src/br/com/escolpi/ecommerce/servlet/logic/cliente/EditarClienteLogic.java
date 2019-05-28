package br.com.escolpi.ecommerce.servlet.logic.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarClienteLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Cliente cliente = new Cliente();
		ClienteDao clienteDao = new ClienteDao();

		if (!StringUtil.isBlank(req.getParameter("id")))
			cliente = clienteDao.obter(Long.valueOf(req.getParameter("id")));

		req.setAttribute("cliente", cliente);

		return "admin/cliente/editar.jsp";
	}

}
