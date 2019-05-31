package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;

public class SalvarVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Vendedor vendedor = new Vendedor();
		VendedorDao vendedorDao = new VendedorDao();

		if (req.getParameter("id") != null)
			vendedor.setId(Long.valueOf(req.getParameter("id")));

		vendedor.setDepartamento(req.getParameter("departamento"));
		vendedor.setEmail(req.getParameter("email"));
		vendedor.setNome(req.getParameter("nome"));
		vendedor.setEmail(req.getParameter("email"));
		vendedor.setPercentualComissao(NumberUtil.parseDouble(req.getParameter("percComissao")));
		String feedback = "incluído";

		if (vendedor.getId() != null && vendedor.getId() > 0) {
			vendedorDao.alterar(vendedor);
			feedback = "alterado";
		} else {
			vendedorDao.adicionar(vendedor);
		}

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Vendedor " + feedback + " com sucesso!"));

		return "mvc?logica=ListarVendedor";
	}

}
