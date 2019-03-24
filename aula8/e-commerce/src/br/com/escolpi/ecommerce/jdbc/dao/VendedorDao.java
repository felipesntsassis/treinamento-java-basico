package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Vendedor;

public class VendedorDao extends GenericDao<Vendedor> {

	@Override
	public void adicionar(Vendedor entidade) {
		String sql = "INSERT INTO vendedores (nome, email, departamento, percentual_comissao) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, entidade.getNome());
			stmt.setString(2, entidade.getEmail());
			stmt.setString(3, entidade.getDepartamento());
			stmt.setDouble(4, entidade.getPercentualComissao());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Vendedor entidade) {
		String sql = "UPDATE vendedores SET nome = ?, email = ?, departamento = ?, percentual_comissao = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, entidade.getNome());
			stmt.setString(2, entidade.getEmail());
			stmt.setString(3, entidade.getDepartamento());
			stmt.setDouble(4, entidade.getPercentualComissao());
			stmt.setLong(5, entidade.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Vendedor> listar() {
		List<Vendedor> vendedores = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM vendedores ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				vendedores.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return vendedores;
	}

	@Override
	public Vendedor obter(Long id) {
		String sql = "SELECT * FROM vendedores WHERE id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
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
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM vendedores WHERE id = ?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
			System.out.println("Excluído!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Vendedor popularEntidade(ResultSet rs) throws SQLException {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(rs.getLong("id"));
		vendedor.setNome(rs.getString("nome"));
		vendedor.setEmail(rs.getString("email"));
		vendedor.setDepartamento(rs.getString("departamento"));
		vendedor.setPercentualComissao(rs.getDouble("percentual_comissao"));

		return vendedor;
	}

}
