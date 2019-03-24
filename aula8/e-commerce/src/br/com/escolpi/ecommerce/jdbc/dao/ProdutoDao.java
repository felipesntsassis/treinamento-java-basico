package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Produto;

public class ProdutoDao extends GenericDao<Produto> {

	public ProdutoDao() {
		super();
	}

	@Override
	public void adicionar(Produto produto) {
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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Produto entidade) {
		String sql = "UPDATE produtos SET categoria_id = ?, descricao = ?, quantidade = ?, preco =  ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, entidade.getCategoriaId());
			stmt.setString(2, entidade.getDescricao());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getPreco());
			stmt.setLong(4, entidade.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produtos ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				produtos.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return produtos;
	}

	@Override
	public Produto obter(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produtos WHERE id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public void remover(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM produtos WHERE id = ?");
			stmt.setLong(1, id);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Produto popularEntidade(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId(rs.getLong("id"));
		produto.setCategoriaId(rs.getLong("categoria_id"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setPreco(rs.getDouble("preco"));

		return produto;
	}

}
