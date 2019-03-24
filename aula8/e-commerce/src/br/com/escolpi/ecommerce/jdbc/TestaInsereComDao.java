package br.com.escolpi.ecommerce.jdbc;

import java.util.Calendar;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;

public class TestaInsereComDao {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNome("Ayrton Senna da Silva");
		cliente.setEmail("senna.ayrton@gmail.com");
		cliente.setEndereco("R. Apuco Tavares de Souza, 143 - Vila São José, Ourinhos - SP");
		cliente.setDataNascimento(Calendar.getInstance());
		cliente.getDataNascimento().set(Calendar.DAY_OF_MONTH, 6);
		cliente.getDataNascimento().set(Calendar.MONTH, 2);
		cliente.getDataNascimento().set(Calendar.YEAR, 1985);

		ClienteDao clienteDao = new ClienteDao();
		clienteDao.adicionar(cliente);
		
		System.out.println("Cliente cadastrado com sucesso!");
	}

}
