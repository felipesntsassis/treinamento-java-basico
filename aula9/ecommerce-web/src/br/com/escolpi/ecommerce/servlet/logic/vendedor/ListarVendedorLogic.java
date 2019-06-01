package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jpa.repository.vendedor.VendedorRepository;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		VendedorRepository repository = new VendedorRepository(ENTITY_MANAGER);
		List<Vendedor> vendedores = repository .listarTodos();
		List<DataTable<Vendedor>> dataTable = new ArrayList<>();

		vendedores.forEach(vendedor -> {
			DataTable<Vendedor> registro = new DataTable<Vendedor>();
			registro.setEntity(vendedor);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(vendedor.getId(), "editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(vendedor.getId(), "excluir", "text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});

		req.setAttribute("vendedores", dataTable);

		return "admin/vendedor/lista.jsp";
	}

}
