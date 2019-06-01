package br.com.escolpi.ecommerce.servlet.logic.produto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirProdutoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		
		Long id = Long.valueOf(req.getParameter("id"));
		ProdutoDao dao = new ProdutoDao();
		if (dao.produtoConsumidoPorItensDePedidos(id)) {
			req.setAttribute("feedback", new Feedback(TipoFeedback.ALERTA, 
					"Este produto é consumido por um ou mais itens de pedidos e não pode ser excluído!"));
		} else {
			dao.remover(id);
			req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
					"Produto excluído com sucesso!"));
		}

		return "mvc?logica=ListarProduto";
	}

}
