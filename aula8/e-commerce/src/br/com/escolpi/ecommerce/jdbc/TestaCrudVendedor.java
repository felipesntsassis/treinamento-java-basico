package br.com.escolpi.ecommerce.jdbc;

import java.util.List;

import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;

public class TestaCrudVendedor implements TestaCrud {

	private VendedorDao dao = new VendedorDao();

	public static void main(String[] args) {
		TestaCrudVendedor crud = new TestaCrudVendedor();
		crud.criar();
		crud.obter();
		crud.alterar();
		crud.remover();
		crud.listar();
	}

	@Override
	public void criar() {
		for (int i = 0; i < TOTAL_REGISTROS; i++) {
			Vendedor vendedor = new Vendedor();
			vendedor.setNome(faker.name().firstName() + " " + faker.name().lastName());
			vendedor.setEmail(faker.internet().emailAddress(faker.letterify("?????_teste")));
			vendedor.setDepartamento(faker.lorem().sentence(1));
			vendedor.setPercentualComissao(faker.number().randomDouble(2, 3, 30));
			dao.adicionar(vendedor);
		}
	}

	@Override
	public void obter() {
		Vendedor vendedor = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (vendedor != null) {
			System.out.println("ID:						" + vendedor.getId());
			System.out.println("Nome:					" + vendedor.getNome());
			System.out.println("E-mail:					" + vendedor.getEmail());
			System.out.println("Departamento:			" + vendedor.getDepartamento());
			System.out.println("Comissão por venda (%):	" + vendedor.getPercentualComissao());
			System.out.println("=============================================================\n");
		} else {
			System.out.println("Registro não encontrado.");
		}
	}

	@Override
	public void alterar() {
		Vendedor vendedor = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (vendedor != null) {
			vendedor.setDepartamento(faker.lorem().sentence(1));
			vendedor.setPercentualComissao(faker.number().randomDouble(2, 3, 30));
			dao.alterar(vendedor);
		}
	}

	@Override
	public void remover() {
		Vendedor vendedor = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (vendedor != null) {
			dao.remover(vendedor.getId());
		}
	}

	@Override
	public void listar() {
		List<Vendedor> vendedores = dao.listar();
		System.out.println("Relação de Vendedores =======================================\n");
		vendedores.forEach(vendedor -> {
			System.out.println("ID:						" + vendedor.getId());
			System.out.println("Nome:					" + vendedor.getNome());
			System.out.println("E-mail:					" + vendedor.getEmail());
			System.out.println("Departamento:			" + vendedor.getDepartamento());
			System.out.println("Comissão por venda (%):	" + vendedor.getPercentualComissao());
			System.out.println("=============================================================\n");
		});
	}

}
