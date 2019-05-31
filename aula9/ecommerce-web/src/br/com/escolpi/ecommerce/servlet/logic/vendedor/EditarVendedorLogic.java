package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Vendedor vendedor = new Vendedor();
		VendedorDao vendedorDao = new VendedorDao();

		if (!StringUtil.isBlank(req.getParameter("id")))
			vendedor = vendedorDao.obter(Long.valueOf(req.getParameter("id")));

		req.setAttribute("vendedor", vendedor);

		return "admin/vendedor/editar.jsp";
	}

}
