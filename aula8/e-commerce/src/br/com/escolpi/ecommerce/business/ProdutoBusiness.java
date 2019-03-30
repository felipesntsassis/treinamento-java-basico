package br.com.escolpi.ecommerce.business;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.ItemPedido;
import br.com.escolpi.ecommerce.modelo.Produto;

public class ProdutoBusiness {

	private ProdutoDao dao;

	public ProdutoBusiness() {
		dao = new ProdutoDao();
	}

	public Produto obter(Long id) {
		return dao.obter(id);
	}

	public void retirarDoEstoque(ItemPedido itemPedido) {
		Produto produtoEstoque = itemPedido.getProduto();
		produtoEstoque.setQuantidade(
				produtoEstoque.getQuantidade() - itemPedido.getQuantidade()
		);
		dao.alterar(produtoEstoque);
	}

}
