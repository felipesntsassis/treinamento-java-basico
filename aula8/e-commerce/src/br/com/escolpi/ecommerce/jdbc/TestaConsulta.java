package br.com.escolpi.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;

public class TestaConsulta {

	public static void main(String[] args) throws SQLException {
		Connection connection =  ConnectionFactory.getConnection();
		String sql = "SELECT * FROM produtos";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Produto> produtos = new ArrayList<>();
		
		while (rs.next()) {
			Produto produto = new Produto();
			produto.setId(rs.getLong("id"));
			produto.setCategoria(new Categoria(rs.getLong("categoria_id")));
			produto.setDescricao(rs.getString("descricao"));
			produto.setQuantidade(rs.getInt("quantidade"));
			produto.setPreco(rs.getDouble("preco"));
			produtos.add(produto);
		}
		
		stmt.close();
		connection.close();
		
		produtos.forEach(produto -> {
			System.out.println("ID:				" + produto.getId());
			System.out.println("Categoria ID:	" + produto.getCategoria());
			System.out.println("Descrição:		" + produto.getDescricao());
			System.out.println("Quantidade:		" + produto.getQuantidade());
			System.out.println("Preço (R$):		" + produto.getPreco());
			System.out.println("===================================================\n");
		});
	}

}
