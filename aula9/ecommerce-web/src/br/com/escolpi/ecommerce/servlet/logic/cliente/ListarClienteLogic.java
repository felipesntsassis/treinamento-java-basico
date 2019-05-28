package br.com.escolpi.ecommerce.servlet.logic.cliente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;

public class ListarClienteLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clientes = clienteDao .listar();
		req.setAttribute("clientes", clientes);

		return "admin/cliente/lista.jsp";
	}

}
