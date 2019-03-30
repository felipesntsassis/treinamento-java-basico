package br.com.escolpi.ecommerce.jdbc;

import java.util.List;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;

public class TestaCrudProduto implements TestaCrud {

	public ProdutoDao dao = new ProdutoDao();

	public static void main(String[] args) {
		TestaCrudProduto crud = new TestaCrudProduto();
//		crud.criar();
//		crud.obter();
//		crud.alterar();
//		crud.remover();
		crud.listar();
	}

	@Override
	public void criar() {
		for (int i = 0; i < TOTAL_REGISTROS; i++) {
			Produto produto = new Produto();
			produto.setCategoria(new Categoria(Long.valueOf(faker.number().numberBetween(1, 10))));
			produto.setDescricao(faker.lorem().sentence(1));
			produto.setQuantidade(faker.number().randomDigit());
			produto.setPreco(faker.number().randomDouble(2, 5, 150));
			dao.adicionar(produto);
		}
	}

	@Override
	public void obter() {
		Produto produto = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (produto != null) {
			exibirDados(produto);
			System.out.println("=============================================================\n");
		} else {
			System.out.println("Registro não encontrado.");
		}
	}

	@Override
	public void alterar() {
		Produto produto = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (produto != null) {
			produto.setDescricao(faker.lorem().sentence(1));
			produto.setQuantidade(faker.number().randomDigit());
			produto.setPreco(faker.number().randomDouble(2, 5, 150));
			dao.alterar(produto);
		}
	}

	@Override
	public void remover() {
		Produto produto = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (produto != null) {
			dao.remover(produto.getId());
		}
	}

	@Override
	public void listar() {
		List<Produto> produtos = dao.listar();
		System.out.println("Relação de Produtos =========================================\n");
		produtos.forEach(produto -> {
			exibirDados(produto);
			System.out.println("=============================================================\n");
		});
	}

	public void exibirDados(Produto produto) {
		System.out.println("ID:			" + produto.getId());
		System.out.println("Categoria:	" + produto.getCategoria().getDescricao());
		System.out.println("Descrição:	" + produto.getDescricao());
		System.out.println("Quantidade:	" + produto.getQuantidade());
		System.out.println("Preço (R$):	" + produto.getPreco());
	}

}
