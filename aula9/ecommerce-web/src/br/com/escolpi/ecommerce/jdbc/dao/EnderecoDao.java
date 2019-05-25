package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.enumerador.Estados;
import br.com.escolpi.ecommerce.modelo.Endereco;

public class EnderecoDao extends GenericDao<Endereco> {

	@Override
	public void adicionar(Endereco endereco) {
		String sql = "INSERT INTO enderecos (cliente_id, cep, logradouro, numero, "
				+ "bairro, complemento, estado_id, municipio, endereco_principal) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, endereco.getClienteId());
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getLogradouro());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setInt(7, endereco.getEstado().getCodigoIbge());
			stmt.setString(8, endereco.getMunicipio());
			stmt.setBoolean(9, endereco.isEnderecoPrincipal());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void alterar(Endereco endereco) {
		String sql = "UPDATE enderecos SET cliente_id = ?, cep = ?, logradouro = ?, numero = ?,"
				+ "bairro = ?, complemento = ?, estado_id= ?, municipio= ?,"
				+ "endereco_principal = ? WHERE id = ?";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, endereco.getClienteId());
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getLogradouro());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setInt(7, endereco.getEstado().getCodigoIbge());
			stmt.setString(8, endereco.getMunicipio());
			stmt.setBoolean(9, endereco.isEnderecoPrincipal());
			stmt.setLong(10, endereco.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Endereco> listar() {
		String sql = "SELECT * FROM enderecos ORDER BY id";
		List<Endereco> enderecos = new ArrayList<>();

		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				enderecos.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return enderecos;
	}

	@Override
	public Endereco obter(Long id) {
		Endereco endereco = new Endereco();
		String sql = "SELECT * FROM enderecos WHERE id = ?";

		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				endereco = popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return endereco;
	}

	@Override
	public void remover(Long id) {
		String sql = "DELETE FROM enderecos WHERE id = ?";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
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
	public Endereco popularEntidade(ResultSet rs) throws SQLException {
		Endereco endereco = new Endereco();
		endereco.setId(rs.getLong("id"));
		endereco.setClienteId(rs.getLong("cliente_id"));
		endereco.setCep(rs.getString("cep"));
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setNumero(rs.getString("numero"));
		endereco.setComplemento(rs.getString("complemento"));
		endereco.setEstado(Estados.obterPorCodigo(rs.getInt("estado_id")));
		endereco.setMunicipio(rs.getString("municipio"));
		endereco.setEnderecoPrincipal(rs.getBoolean("endereco_principal"));

		return endereco;
	}

}
