package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.escolpi.ecommerce.jdbc.ConnectionFactory;
import br.com.escolpi.ecommerce.modelo.Produto;

public class ProdutoDao {

	private Connection connection;
	
	public ProdutoDao() {
		connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Produto produto) {
		String sql = "INSERT INTO produtos (categoria_id, descricao, quantidade, preco) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, produto.getCategoriaId());
			stmt.setString(2, produto.getDescricao());
			stmt.setInt(3, produto.getQuantidade());
			stmt.setDouble(4, produto.getPreco());
			
			stmt.execute();
			stmt.close();
			System.out.println("Gravado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
