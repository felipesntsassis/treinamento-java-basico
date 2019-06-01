package br.com.escolpi.ecommerce.servlet.logic.produto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.OptionList;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarProdutoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Produto produto = new Produto();
		ProdutoDao dao = new ProdutoDao();
		CategoriaDao categoriaDao = new CategoriaDao();
		List<OptionList> comboCategoria = new ArrayList<>();
		List<Categoria> categorias = categoriaDao.listar();
		categorias.forEach(categoria -> comboCategoria.add(new OptionList(categoria.getId(), categoria.getDescricao())));

		if (!StringUtil.isBlank(req.getParameter("id")))
			produto = dao.obter(Long.valueOf(req.getParameter("id")));

		req.setAttribute("produto", produto);
		req.setAttribute("categorias", comboCategoria);

		return "admin/produto/editar.jsp";
	}

}
