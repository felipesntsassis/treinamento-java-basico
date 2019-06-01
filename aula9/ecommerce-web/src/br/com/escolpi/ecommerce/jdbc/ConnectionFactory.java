package br.com.escolpi.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionFactory {

	public static String JDBC_URL_DATABASE = "jdbc:mysql://localhost/ecommerce?characterEncoding=utf8";

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			DriverManager.setLoginTimeout(0);
			return DriverManager.getConnection(JDBC_URL_DATABASE, "root", "q1w2e3r4");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
