package br.com.escolpi.ecommerce.servlet.logic.departamento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.modelo.Departamento;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarDepartamentoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		DepartamentoRepository repository = new DepartamentoRepository(ENTITY_MANAGER);
		List<Departamento> departamentos = repository.listarTodos();
		List<DataTable<Departamento>> dataTable = new ArrayList<>();
		
		departamentos.forEach(departamento -> {
			DataTable<Departamento> registro = new DataTable<>();
			registro.setEntity(departamento);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(departamento.getId(), 
					"editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(departamento.getId(), "excluir", 
					"text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});
		
		req.setAttribute("departamentos", dataTable);

		return "admin/departamento/lista.jsp";
	}

}
