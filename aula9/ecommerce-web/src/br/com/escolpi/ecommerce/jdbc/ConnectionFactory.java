package br.com.escolpi.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection("jdbc:mysql://localhost/ecommerce", "root", "q1w2e3r4");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
