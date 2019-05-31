package br.com.escolpi.ecommerce.servlet.logic.categoria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarCategoriaLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoriaDao dao = new CategoriaDao();
		List<Categoria> categorias = dao.listar();
		List<DataTable<Categoria>> dataTable = new ArrayList<>();
		
		categorias.forEach(categoria -> {
			DataTable<Categoria> registro = new DataTable<Categoria>();
			registro.setEntity(categoria);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(categoria.getId(), "editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(categoria.getId(), "excluir", "text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});

		req.setAttribute("categorias", dataTable);

		return "admin/categoria/lista.jsp";
	}

}
