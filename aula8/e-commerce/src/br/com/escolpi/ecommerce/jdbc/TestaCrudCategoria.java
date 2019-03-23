package br.com.escolpi.ecommerce.jdbc;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;

public class TestaCrudCategoria {

	private static CategoriaDao dao = new CategoriaDao();
	private static Faker faker = new Faker(new Locale("pt-BR"));

	public static void main(String[] args) {
		criar(); // C - Create (criar)
		obter(); // R - Read (Ler)
		alterar(); // U - Update (Alterar)
		remover(); // D - Delete (Apagar/Remover)
	}

	private static void obter() {
		Categoria categoria = dao.obter(Long.valueOf(faker.number().numberBetween(5, 8)));
		
		if (categoria.getId() != null) {
			System.out.println("Categoria consultada com sucesso!");
			System.out.println("ID:			" + categoria.getId());
			System.out.println("Descrição:	" + categoria.getDescricao());
			System.out.println("============================================\n");
		} else {
			System.out.println("Registro não encontrado.");
		}
	}

	private static void criar() {
		dao.adicionar(new Categoria(faker.lorem().sentence(1)));
	}


	private static void alterar() {
		Categoria categoria = dao.obter(Long.valueOf(faker.number().numberBetween(5, 8)));
		categoria.setDescricao(faker.lorem().sentence(1));
		dao.alterar(categoria);
		System.out.println("Categoria alterada com sucesso!");
		System.out.println("Categoria consultada com sucesso!");
		System.out.println("ID:			" + categoria.getId());
		System.out.println("Descrição:	" + categoria.getDescricao());
		System.out.println("============================================\n");
	}

	private static void remover() {
		Categoria categoria = dao.obter(13L);
		dao.remover(categoria.getId());
		System.out.println("Categoria " + categoria.getId() + " removida com sucesso!");

	}
}
