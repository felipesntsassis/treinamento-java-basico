package br.com.escolpi.ecommerce.servlet.logic.endereco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarEnderecoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long clienteId =  Long.valueOf(req.getParameter("clienteId"));
		EnderecoDao dao = new EnderecoDao();
		ClienteDao clienteDao = new ClienteDao();

		Set<Endereco> enderecos= dao.listarPorClienteId(clienteId);
		List<DataTable<Endereco>> dataTable = new ArrayList<DataTable<Endereco>>();
		enderecos.forEach(endereco -> {
			DataTable<Endereco> registro = new DataTable<Endereco>();
			registro.setEntity(endereco);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(endereco.getId(), "editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(endereco.getId(), "excluir", "text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});

		req.setAttribute("cliente", clienteDao.obter(clienteId));
		req.setAttribute("clienteId", clienteId);
		req.setAttribute("enderecos", dataTable);

		return "admin/cliente/endereco/lista.jsp";
	}

}
