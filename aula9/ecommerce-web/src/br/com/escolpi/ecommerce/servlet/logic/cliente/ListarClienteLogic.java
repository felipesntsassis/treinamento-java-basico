package br.com.escolpi.ecommerce.servlet.logic.cliente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarClienteLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clientes = clienteDao .listar();
		List<DataTable<Cliente>> dataTable = new ArrayList<>();

		clientes.forEach(cliente -> {
			DataTable<Cliente> registro = new DataTable<Cliente>();
			registro.setEntity(cliente);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(cliente.getId(), "editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(cliente.getId(), "enderecos", "text-primary", "fa-map-signs", "Endereços"));
			registro.getMenu().add(new OptionMenu(cliente.getId(), "excluir", "text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});

		req.setAttribute("clientes", dataTable);

		return "admin/cliente/lista.jsp";
	}

}
