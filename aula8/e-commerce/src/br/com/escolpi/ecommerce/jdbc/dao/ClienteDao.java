package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.escolpi.ecommerce.jdbc.ConnectionFactory;
import br.com.escolpi.ecommerce.modelo.Cliente;

public class ClienteDao {

	private Connection connection;

	public ClienteDao() {
		connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Cliente cliente) {
		String sql = "INSERT INTO clientes (nome, email, endereco, data_nascimento) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getEndereco());
			stmt.setDate(4, new Date(cliente.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
			System.out.println("Gravado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
