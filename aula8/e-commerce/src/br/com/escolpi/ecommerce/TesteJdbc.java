package br.com.escolpi.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteJdbc {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/ecommerce", "root", "q1w2e3r4");
		System.out.println("Conectou!");
		connection.close();
	}

}
