package br.com.escolpi.ecommerce.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;

public class TestaInsereCategoria {

	// Forma menos simples
//	public static void main(String[] args) {
//		Categoria categoria1 = new Categoria();
//		categoria1.setDescricao("Pizzas");
//
//		Categoria categoria2 = new Categoria();
//		categoria2.setDescricao("Lanches");
//
//		Categoria categoria3 = new Categoria();
//		categoria3.setDescricao("Bebidas");
//		
//		Categoria categoria4 = new Categoria();
//		categoria4.setDescricao("Sobremesas");
//
//		List<Categoria> categorias = new ArrayList<>();
//		categorias.add(categoria1);
//		categorias.add(categoria2);
//		categorias.add(categoria3);
//		categorias.add(categoria4);
//
//		CategoriaDao categoriaDao = new CategoriaDao();
//
//		for (Categoria categoria : categorias) {
//			categoriaDao.adiciona(categoria);
//		}
//
//		System.out.println("Categorias inclusas com sucesso!");
//	}

	// Forma mais simples
	public static void main(String[] args) {
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = new ArrayList<>(
			Arrays.asList(
				new Categoria("Pizzas"),
				new Categoria("Lanches"),
				new Categoria("Bebidas"),
				new Categoria("Sobremesas")
			)
		);
		categorias.forEach(categoria -> categoriaDao.adicionar(categoria));
		
		System.out.println("Categorias inclusas com sucesso!");
	}

}
