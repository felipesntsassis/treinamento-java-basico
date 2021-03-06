package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.escolpi.ecommerce.enumerador.SituacaoPedido;
import br.com.escolpi.ecommerce.modelo.Pedido;

public class PedidoDao extends GenericDao<Pedido> {

	private ClienteDao clienteDao;
	private VendedorDao vendedorDao;

	public PedidoDao() {
		super();
		clienteDao = new ClienteDao();
		vendedorDao = new VendedorDao();
	}

	@Override
	public void adicionar(Pedido entidade) {
		String sql = "INSERT INTO pedidos (vendedor_id, cliente_id, data_pedido, situacao) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, entidade.getVendedor().getId());
			stmt.setLong(2, entidade.getCliente().getId());
			stmt.setDate(3, new Date(entidade.getDataPedido().getTimeInMillis()));
			stmt.setInt(4, entidade.getSituacao().ordinal());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Pedido entidade) {
		String sql = "UPDATE pedidos SET vendedor_id = ?, cliente_id = ?, data_pedido = ?, situacao = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, entidade.getVendedor().getId());
			stmt.setLong(2, entidade.getCliente().getId());
			stmt.setDate(3, new Date(entidade.getDataPedido().getTimeInMillis()));
			stmt.setInt(4, entidade.getSituacao().ordinal());
			stmt.setLong(5, entidade.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Pedido> listar() {
		List<Pedido> pedidos = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pedidos ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pedidos.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pedidos;
	}

	@Override
	public Pedido obter(Long id) {
		String sql = "SELECT * FROM pedidos WHERE id = ?";

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
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM pedidos WHERE id = ?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Pedido obterUltimoPedido() {
		String sql = "SELECT * FROM pedidos ORDER BY id DESC LIMIT 1";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
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
	public Pedido popularEntidade(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();
		pedido.setId(rs.getLong("id"));
		pedido.setVendedor(vendedorDao.obter(rs.getLong("vendedor_id")));
		pedido.setCliente(clienteDao.obter(rs.getLong("cliente_id")));
		// TODO: Criar método utilitário para converter data para calendar
		Calendar dataPedido = Calendar.getInstance();
		dataPedido.setTime(rs.getDate("data_pedido"));
		pedido.setDataPedido(dataPedido);
		pedido.setSituacao(SituacaoPedido.obter(rs.getInt("situacao")));

		return pedido;
	}

}
