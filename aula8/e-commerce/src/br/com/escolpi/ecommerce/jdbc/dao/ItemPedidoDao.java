package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.ecommerce.modelo.ItemPedido;

public class ItemPedidoDao extends GenericDao<ItemPedido> {

	private PedidoDao pedidoDao;
	private ProdutoDao produtoDao;

	public ItemPedidoDao() {
		super();
		pedidoDao = new PedidoDao();
		produtoDao = new ProdutoDao();
	}

	@Override
	public void adicionar(ItemPedido entidade) {
		String sql = "INSERT INTO itens_pedidos (pedido_id, produto_id, quantidade, valor_item) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, entidade.getPedido().getId());
			stmt.setLong(2, entidade.getProduto().getId());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getValor());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(ItemPedido entidade) {
		String sql = "UPDATE itens_pedidos SET pedido_id = ?, produto_id = ?, quantidade = ?, "
				+ "valor+item = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, entidade.getPedido().getId());
			stmt.setLong(2, entidade.getProduto().getId());
			stmt.setInt(3, entidade.getQuantidade());
			stmt.setDouble(4, entidade.getValor());
			stmt.setLong(5, entidade.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ItemPedido> listar() {
		List<ItemPedido> itens = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM itens_pedidos ORDER BY id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itens.add(popularEntidade(rs));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return itens;
	}

	@Override
	public ItemPedido obter(Long id) {
		String sql = "SELECT * FROM itens_pedidos WHERE id = ?";

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
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM itens_pedidos WHERE id = ?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ItemPedido popularEntidade(ResultSet rs) throws SQLException {
		ItemPedido item = new ItemPedido();
		item.setId(rs.getLong("id"));
		item.setPedido(pedidoDao.obter(rs.getLong("pedido_id")));
		item.setProduto(produtoDao.obter(rs.getLong("produto_id")));
		item.setQuantidade(rs.getInt("quantidade"));
		item.setValor(rs.getDouble("valor_item"));

		return item;
	}

}
