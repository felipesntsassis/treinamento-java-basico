package br.com.escolpi.ecommerce.servlet.logic.produto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;

public class SalvarProdutoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Produto produto = new Produto();
		ProdutoDao dao = new ProdutoDao();

		if (req.getParameter("id") != null)
			produto.setId(Long.valueOf(req.getParameter("id")));

		produto.setCategoria(new Categoria(Long.valueOf(req.getParameter("categoria"))));
		produto.setDescricao(req.getParameter("descricao"));
		produto.setPreco(NumberUtil.parseDouble(req.getParameter("preco")));
		produto.setQuantidade(Integer.valueOf(req.getParameter("quantidade")));
		
		String feedback = "incluído";
		
		if (!NumberUtil.isNullOrZero(produto.getId())) {
			feedback = "alterado";
			dao.alterar(produto);
		} else {
			dao.adicionar(produto);
		}

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Produto " + feedback + " com sucesso!"));

		return "mvc?logica=ListarProduto";
	}

}
