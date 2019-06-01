package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Categoria;

public class CategoriaDao extends GenericDao<Categoria> {

	@Override
	public void adicionar(Categoria categoria) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("INSERT INTO categorias (descricao) VALUES (?)");
			stmt.setString(1, categoria.getDescricao());

			stmt.execute();
			stmt.close();
			System.out.println("Gravado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void alterar(Categoria entidade) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("UPDATE categorias SET descricao = ? WHERE id = ?");
			stmt.setString(1, entidade.getDescricao());
			stmt.setLong(2, entidade.getId());

			stmt.execute();
			stmt.close();
			System.out.println("Altearado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();

		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM categorias ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categorias.add(popularEntidade(rs));
			}

			stmt.close();
			System.out.println("Listado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return categorias;
	}

	@Override
	public Categoria obter(Long id) {
		Categoria categoria = new Categoria();
		
		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM categorias WHERE id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categoria = popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return categoria;
	}

	@Override
	public void remover(Long id) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("DELETE FROM categorias WHERE id = ?");
			stmt.setLong(1, id);

			stmt.execute();
			stmt.close();
			System.out.println("Excluído!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public Categoria popularEntidade(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(rs.getLong("id"));
		categoria.setDescricao(rs.getString("descricao"));

		return categoria;
	}

	public boolean categoriaReferenciadaEmProdutos(Long id) {
		String sql = "SELECT COUNT(*) FROM produtos WHERE categoria_id = ?";
		int total = 0;

		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				total = rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return total > 0;
	}

}
