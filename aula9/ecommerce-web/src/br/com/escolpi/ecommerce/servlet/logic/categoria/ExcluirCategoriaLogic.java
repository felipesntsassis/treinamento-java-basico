package br.com.escolpi.ecommerce.servlet.logic.categoria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirCategoriaLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		
		Long id = Long.valueOf(req.getParameter("id"));
		CategoriaDao dao = new CategoriaDao();
		if (dao.categoriaReferenciadaEmProdutos(id)) {
			req.setAttribute("feedback", new Feedback(TipoFeedback.ALERTA, 
					"Esta categoria está vinculada a um ou mais produtos e não pode ser excluída!"));
		} else {
			dao.remover(id);
			req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
					"Categoria excluída com sucesso!"));
		}

		return "mvc?logica=ListarCategoria";
	}

}
