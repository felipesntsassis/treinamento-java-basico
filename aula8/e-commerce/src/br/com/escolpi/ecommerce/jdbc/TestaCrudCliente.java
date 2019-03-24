package br.com.escolpi.ecommerce.jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;

public class TestaCrudCliente implements TestaCrud {

	private ClienteDao dao = new ClienteDao();

	public static void main(String[] args) {
		TestaCrudCliente crud = new TestaCrudCliente();
		crud.criar();
		crud.obter();
		crud.alterar();
		crud.remover();
		crud.listar();
	}

	@Override
	public void criar() {
		for (int i = 0; i < TOTAL_REGISTROS; i++) {
			Cliente cliente = new Cliente();
			cliente.setNome(faker.name().firstName() + " " + faker.name().lastName());
			cliente.setEmail(faker.internet().emailAddress(faker.letterify("?????_teste")));
			cliente.setEndereco(faker.address().fullAddress());
			cliente.setDataNascimento(Calendar.getInstance());
			cliente.getDataNascimento().set(Calendar.YEAR, faker.number().numberBetween(1920, 2000));
			cliente.getDataNascimento().set(Calendar.MONTH, faker.number().numberBetween(0, 11));
			cliente.getDataNascimento().set(Calendar.DAY_OF_WEEK, faker.number().numberBetween(1, (
					cliente.getDataNascimento().get(Calendar.MONTH) == 1) ? 28 : 30));
			dao.adicionar(cliente);
		}
	}

	@Override
	public void obter() {
		Cliente cliente = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (cliente != null) {
			System.out.println("ID:					" + cliente.getId());
			System.out.println("Nome:				" + cliente.getNome());
			System.out.println("E-mail:				" + cliente.getEmail());
			System.out.println("Endereço:			" + cliente.getEndereco());
			System.out.println("Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(
					cliente.getDataNascimento().getTime()));
			System.out.println("=============================================================\n");
		} else {
			System.out.println("Registro não encontrado.");
		}
	}

	@Override
	public void alterar() {
		Cliente cliente = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (cliente != null) {
			cliente.setNome(faker.name().firstName() + " " + faker.name().lastName());
			cliente.setEmail(faker.internet().emailAddress(faker.letterify("?????_teste")));
			cliente.setEndereco(faker.address().fullAddress());
			dao.alterar(cliente);
		}
	}

	@Override
	public void remover() {
		Cliente cliente = dao.obter(Long.valueOf(faker.number().numberBetween(1, 10)));

		if (cliente != null) {
			dao.remover(cliente.getId());
		}
	}

	@Override
	public void listar() {
		List<Cliente> clientes = dao.listar();
		System.out.println("Relação de Clientes =========================================\n");
		clientes.forEach(cliente -> {
			System.out.println("ID:					" + cliente.getId());
			System.out.println("Nome:				" + cliente.getNome());
			System.out.println("E-mail:				" + cliente.getEmail());
			System.out.println("Endereço:			" + cliente.getEndereco());
			System.out.println("Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(
					cliente.getDataNascimento().getTime()));
			System.out.println("=============================================================\n");
		});
	}

}
