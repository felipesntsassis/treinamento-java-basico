package br.com.escolpi.ecommerce.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Produto;

public class TestaInsereProduto {

	public static void main(String[] args) {
		ProdutoDao produtoDao = new ProdutoDao();
		Faker faker = new Faker(new Locale("pt-BR"));
		List<Long> categoriasId = new ArrayList<>(Arrays.asList(5L, 6L, 7L, 8L));
		
		categoriasId.forEach(categoria -> {
			for (int i = 0; i < 3; i++) {
				Produto produto = new Produto();
				produto.setCategoriaId(categoria);
				produto.setDescricao(faker.food().ingredient());
				produto.setPreco(faker.number().randomDouble(2, 5, 150));
				produto.setQuantidade(faker.number().randomDigit());
				produtoDao.adiciona(produto);
			}
		});
	}

}
