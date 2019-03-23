package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Categoria;

public class CategoriaDao extends GenericDao<Categoria> {

	public CategoriaDao() {
		super();
	}

	@Override
	public void adicionar(Categoria categoria) {
		String sql = "INSERT INTO categorias (descricao) VALUES (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, categoria.getDescricao());

			stmt.execute();
			stmt.close();
			System.out.println("Gravado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Categoria entidade) {
		String sql = "UPDATE categorias SET descricao = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, entidade.getDescricao());
			stmt.setLong(2, entidade.getId());

			stmt.execute();
			stmt.close();
			System.out.println("Altearado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM categorias";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("id"));
				categoria.setDescricao(rs.getString("descricao"));
				categorias.add(categoria);
			}

			stmt.close();
			System.out.println("Listado!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return categorias;
	}

	@Override
	public Categoria obter(Long id) {
		Categoria categoria = new Categoria();
		String sql = "SELECT * FROM categorias WHERE id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categoria.setId(rs.getLong("id"));
				categoria.setDescricao(rs.getString("descricao"));
			}

			stmt.close();
			System.out.println("Obtido!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return categoria;
	}

	@Override
	public void remover(Long id) {
		String sql = "DELETE FROM categorias WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);

			stmt.execute();
			stmt.close();
			System.out.println("Excluído!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
