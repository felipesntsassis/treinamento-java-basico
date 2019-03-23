package br.com.escolpi.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class InsereJdbc {

	public static void main(String[] args) throws SQLException {
		// conectando
		//Connection connection = ConnectionFactory.getConnection();
//		try {
		try (Connection connection = ConnectionFactory.getConnection()) {
			// cria o PreparedStatement
			String sql = "INSERT INTO clientes (nome, email, endereco, data_nascimento) "
					+ "VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, "Machado de Assis");
			stmt.setString(2, "machado.assis@gmail.com");
			stmt.setString(3, "R. Silva Jardim, 365 - Vila Moraes, Ourinhos - SP, 19900-261");
			stmt.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
			
			// executa
			stmt.execute();
			stmt.close();

			System.out.println("Gravado!");
		} catch (SQLException e) {
			System.out.println(e);
		}
//		} finally {
//			connection.close();
//		}
	}
}
