package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.ItemPedido;

public class ItemPedidoDao extends GenericDao<ItemPedido> {

	@Override
	public void adicionar(ItemPedido entidade) {
		String sql = "INSERT INTO itens_pedidos (pedido_id, produto_id, quantidade, valor_item) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, entidade.getPedido().getId());
			stmt.setLong(2, entidade.getProduto().getId());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getValor());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void alterar(ItemPedido entidade) {
		String sql = "UPDATE itens_pedidos SET pedido_id = ?, produto_id = ?, quantidade = ?, "
				+ "valor+item = ? WHERE id = ?";
		try {
			PreparedStatement stmt = openConnection().prepareStatement(sql);
			stmt.setLong(1, entidade.getPedido().getId());
			stmt.setLong(2, entidade.getProduto().getId());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getValor());
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
	public List<ItemPedido> listar() {
		List<ItemPedido> itens = new ArrayList<>();

		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM itens_pedidos ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itens.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return itens;
	}

	public List<ItemPedido> listarPorPedido(Long pedidoId) {
		List<ItemPedido> itens = new ArrayList<>();

		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM itens_pedidos WHERE pedido_id = ? "
					+ "ORDER BY id");
			stmt.setLong(1, pedidoId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itens.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return itens;
	}

	@Override
	public ItemPedido obter(Long id) {
		ItemPedido itemPedido = new ItemPedido();

		try {
			PreparedStatement stmt = openConnection().prepareStatement("SELECT * FROM itens_pedidos WHERE id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemPedido = popularEntidade(rs);
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		return itemPedido;
	}

	@Override
	public void remover(Long id) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("DELETE FROM itens_pedidos WHERE id = ?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removerPorPedido(Long pedidoId) {
		try {
			PreparedStatement stmt = openConnection().prepareStatement("DELETE FROM itens_pedidos WHERE pedido_id = ?");
			stmt.setLong(1, pedidoId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public ItemPedido popularEntidade(ResultSet rs) throws SQLException {
		PedidoDao pedidoDao = new PedidoDao();
		ProdutoDao produtoDao = new ProdutoDao();
		ItemPedido item = new ItemPedido();
		item.setId(rs.getLong("id"));
		item.setPedido(pedidoDao.obter(rs.getLong("pedido_id")));
		item.setProduto(produtoDao.obter(rs.getLong("produto_id")));
		item.setQuantidade(rs.getInt("quantidade"));
		item.setValor(rs.getDouble("valor_item"));

		return item;
	}

}
