package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;

public class ProdutoDao extends GenericDao<Produto> {

	@Override
	public void adicionar(Produto produto) {
		String sql = "INSERT INTO produtos (categoria_id, descricao, quantidade, preco) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, produto.getCategoria().getId());
			stmt.setString(2, produto.getDescricao());
			stmt.setInt(3, produto.getQuantidade());
			stmt.setDouble(4, produto.getPreco());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void alterar(Produto entidade) {
		String sql = "UPDATE produtos SET categoria_id = ?, descricao = ?, quantidade = ?, preco =  ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, entidade.getCategoria().getId());
			stmt.setString(2, entidade.getDescricao());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getPreco());
			stmt.setLong(5, entidade.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<>();

		try {
			StringBuilder sql = new StringBuilder("SELECT p.*, c.* FROM produtos p ")
				.append("INNER JOIN categorias c ON c.id = p.categoria_id ")
				.append("ORDER BY p.id");
			PreparedStatement stmt = openConnection().prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				produtos.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return produtos;
	}

	@Override
	public Produto obter(Long id) {
		Produto produto = new Produto();
		try {
			StringBuilder sql = new StringBuilder("SELECT p.*, c.* FROM produtos p ")
					.append("INNER JOIN categorias c ON c.id = p.categoria_id ")
					.append("WHERE id = ?");
			PreparedStatement stmt = openConnection().prepareStatement(sql.toString());
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				produto = popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return produto;
	}

	@Override
	public void remover(Long id) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("DELETE FROM produtos WHERE id = ?");
			stmt.setLong(1, id);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public Produto popularEntidade(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId(rs.getLong("p.id"));
//		Quando tinhamos apenas o ID, populamos desta forma:
//		produto.setCategoriaId(rs.getLong("categoria_id"));
//		Porém, trabalhamos com objetos, a prática correta é esta:
		produto.setDescricao(rs.getString("p.descricao"));
		produto.setQuantidade(rs.getInt("p.quantidade"));
		produto.setPreco(rs.getDouble("p.preco"));
		produto.setCategoria(new Categoria(rs.getLong("p.categoria_id")));
		produto.getCategoria().setDescricao(rs.getString("c.descricao"));

		return produto;
	}

}
