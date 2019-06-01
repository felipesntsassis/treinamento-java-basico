package br.com.escolpi.ecommerce.servlet.logic.categoria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarCategoriaLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Categoria categoria = new Categoria();
		CategoriaDao dao = new CategoriaDao();

		if (!StringUtil.isBlank(req.getParameter("id")))
			categoria = dao.obter(Long.valueOf(req.getParameter("id")));

		req.setAttribute("categoria", categoria);

		return "admin/categoria/editar.jsp";
	}

}
