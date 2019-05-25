
package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.Cliente;

public class ClienteDao extends GenericDao<Cliente> {

	@Override
	public void adicionar(Cliente cliente) {
		String sql = "INSERT INTO clientes (nome, email, data_nascimento) "
				+ "VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setDate(3, new Date(cliente.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			cliente.setId(obterId(stmt));
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void alterar(Cliente entidade) {
		String sql = "UPDATE clientes SET nome = ?, email = ?, data_nascimento = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setString(1, entidade.getNome());
			stmt.setString(2, entidade.getEmail());
			stmt.setDate(3, new Date(entidade.getDataNascimento().getTimeInMillis()));
			stmt.setLong(4, entidade.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Cliente> listar() {
		String sql = "SELECT * FROM clientes ORDER BY id";
		List<Cliente> clientes = new ArrayList<>();

		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				clientes.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return clientes;
	}

	@Override
	public Cliente obter(Long id) {
		Cliente cliente = new Cliente();

		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM clientes WHERE id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cliente = popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return cliente;
	}

	@Override
	public void remover(Long id) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("DELETE FROM clientes WHERE id = ?");
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
	public Cliente popularEntidade(ResultSet rs) throws SQLException {
		EnderecoDao enderecoDao = new EnderecoDao();
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setEmail(rs.getString("email"));
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(rs.getDate("data_nascimento"));
		cliente.setDataNascimento(dataNascimento);
		cliente.setEnderecosDeEntrega(enderecoDao.listarPorClienteId(cliente.getId()));
	
		return cliente;
	}

}
