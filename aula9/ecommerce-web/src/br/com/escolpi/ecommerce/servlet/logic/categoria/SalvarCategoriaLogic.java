package br.com.escolpi.ecommerce.servlet.logic.categoria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;

public class SalvarCategoriaLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Categoria categoria = new Categoria();
		CategoriaDao dao = new CategoriaDao();

		if (req.getParameter("id") != null)
			categoria.setId(Long.valueOf(req.getParameter("id")));

		categoria.setDescricao(req.getParameter("descricao"));
		String feedback = "incluída";
		
		if (!NumberUtil.isNullOrZero(categoria.getId())) {
			feedback = "alterada";
			dao.alterar(categoria);
		} else {
			dao.adicionar(categoria);
		}

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Categoria " + feedback + " com sucesso!"));

		return "mvc?logica=ListarCategoria";
	}

}
