package br.com.escolpi.ecommerce;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.escolpi.ecommerce.jdbc.ConnectionFactory;

public class TesteFabricaJdbc {

	public static void main(String[] args) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conectou pela fábrica de conexões!");
		connection.close();
	}

}
