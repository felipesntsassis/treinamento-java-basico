package br.com.escolpi.ecommerce.servlet.delegate;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;

public class ListarCliente {

	public void executa(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clientes = clienteDao.listar();
		req.setAttribute("clientes", clientes);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/cliente/lista.jsp");
		rd.forward(req, resp);
	}

}
