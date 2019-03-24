package br.com.escolpi.ecommerce.jdbc;

import java.util.List;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;

public class TestaCrudCategoria implements TestaCrud {

	public CategoriaDao dao = new CategoriaDao();

	public static void main(String[] args) {
		TestaCrudCategoria crud = new TestaCrudCategoria();
		crud.criar();
		crud.obter();
		crud.alterar();
		crud.remover();
		crud.listar();
	}

	@Override
	public void criar() {
		for (int i = 0; i < TOTAL_REGISTROS; i++) {
			dao.adicionar(new Categoria(faker.lorem().sentence(1)));
		}
	}

	@Override
	public void obter() {
		Categoria categoria = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (categoria != null) {
			System.out.println("ID:			" + categoria.getId());
			System.out.println("Descrição:	" + categoria.getDescricao());
			System.out.println("=============================================================\n");
		} else {
			System.out.println("Registro não encontrado.");
		}
	}

	@Override
	public void alterar() {
		Categoria categoria = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));
		if (categoria != null) {
			categoria.setDescricao(faker.lorem().sentence(1));
			dao.alterar(categoria);
		}
	}

	public void remover() {
		Categoria categoria = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));
		if (categoria != null) {
			dao.remover(categoria.getId());
		}
	}

	@Override
	public void listar() {
		List<Categoria> categorias = dao.listar();
		System.out.println("Relação de Categorias =======================================\n");
		categorias.forEach(categoria -> {
			System.out.println("ID:			" + categoria.getId());
			System.out.println("Descrição:	" + categoria.getDescricao());
			System.out.println("=============================================================\n");
		});
		
	}

}
