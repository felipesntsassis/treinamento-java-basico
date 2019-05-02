package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.escolpi.ecommerce.jdbc.ConnectionFactory;

public abstract class GenericDao<T> implements Dao<T> {

	protected static Connection connection = ConnectionFactory.getConnection();

	public Connection openConnection() throws SQLException {
		if (connection.isClosed()) {
			connection = ConnectionFactory.getConnection();
		}
		
		return connection;
	}

	protected void closeConnection() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao fechar a Conexão: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public long obterId(PreparedStatement stmt) throws SQLException {
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new SQLException("Erro o salvar registro: Nenhum ID obtido");
			}
		}
	}
}
