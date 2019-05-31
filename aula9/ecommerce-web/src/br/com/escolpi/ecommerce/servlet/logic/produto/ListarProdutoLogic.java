package br.com.escolpi.ecommerce.servlet.logic.produto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.DataTable;
import br.com.escolpi.ecommerce.util.OptionMenu;

public class ListarProdutoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ProdutoDao dao = new ProdutoDao();
		List<Produto> produtos = dao.listar();
		List<DataTable<Produto>> dataTable = new ArrayList<>();

		produtos.forEach(produto -> {
			DataTable<Produto> registro = new DataTable<Produto>();
			registro.setEntity(produto);
			registro.setMenu(new HashSet<>());
			registro.getMenu().add(new OptionMenu(produto.getId(), "editar", "text-primary", "fa-edit", "Editar"));
			registro.getMenu().add(new OptionMenu(produto.getId(), "excluir", "text-danger", "fa-trash", "Excluir"));
			dataTable.add(registro);
		});

		req.setAttribute("produtos", dataTable);

		return "admin/produto/lista.jsp";
	}

}
