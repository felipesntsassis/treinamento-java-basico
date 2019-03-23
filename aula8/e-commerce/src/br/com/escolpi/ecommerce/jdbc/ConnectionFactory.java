package br.com.escolpi.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/ecommerce", "root", "q1w2e3r4");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
