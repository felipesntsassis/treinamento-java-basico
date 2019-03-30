package br.com.escolpi.ecommerce.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.escolpi.ecommerce.enumerador.SituacaoPedido;
import br.com.escolpi.ecommerce.jdbc.dao.ItemPedidoDao;
import br.com.escolpi.ecommerce.jdbc.dao.PedidoDao;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.modelo.ItemPedido;
import br.com.escolpi.ecommerce.modelo.Pedido;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.util.DateUtil;

public class CompraBusiness {

	private ItemPedidoDao itemPedidoDao;
	private PedidoDao pedidoDao;

	private ProdutoBusiness produtoBusiness;

	private List<ItemPedido> carrinhoDeCompra;

	public CompraBusiness() {
		itemPedidoDao = new ItemPedidoDao();
		pedidoDao = new PedidoDao();
		produtoBusiness = new ProdutoBusiness();
		carrinhoDeCompra = new ArrayList<>();
	}

	public void adicionarNoCarrinho(Produto produto, Integer quantidade) throws Exception {
		if (!produtoDisponivel(produto)) {
			throw new Exception("Produto não disponível!");
		}
		if (produto.getQuantidade() < quantidade) {
			throw new Exception("A quantidade solicitada nao pode ser atendida: Estoque em baixa.");
		}

		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.calcularValor();
		carrinhoDeCompra.add(item);
		System.out.println("Produto " + produto.getDescricao() + " adicionado no carrinho!");
	}

	public void limparCarrinho() {
		this.carrinhoDeCompra.clear();
	}

	public void comprarProduto(Vendedor vendedor, Cliente cliente) {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setVendedor(vendedor);
		pedido.setDataPedido(Calendar.getInstance());
		pedido.setSituacao(SituacaoPedido.NOVO);
		pedidoDao.adicionar(pedido);
		pedido = pedidoDao.obterUltimoPedido();
		pedido.setItensPedido(new ArrayList<>(carrinhoDeCompra));
		finalizarPedido(pedido);
		emitirNotaCompra(pedido);
	}

	public void emitirNotaCompra(Pedido pedido) {
		StringBuilder nota = new StringBuilder();
		nota.append("==================== ESCOLPI INFORMÁTICA ==================== \n")
			.append("PEDIDO Nº	" + pedido.getId() + "	Data: " 
					+ DateUtil.parseToString(pedido.getDataPedido(), "dd/MM/yyyy"))
			.append("\n")
			.append("Cliente: 		" + pedido.getCliente().getNome() + "\n")
			.append("Atendido por: 	" + pedido.getVendedor().getNome() + " / ")
			.append(pedido.getVendedor().getDepartamento() + "\n")
			.append("Situação: 		" + pedido.getSituacao().getDescricao() + "\n")
			.append("\n")
			.append("Itens do Pedido ============================================= \n");
		pedido.getItensPedido().forEach(item -> 
			nota.append(item.getProduto().getDescricao() + "	")
				.append(item.getQuantidade() + "	")
				.append(item.getValor() + "\n")
		);
		nota.append("=============================================================");
		System.out.println(nota.toString());
	}

	private boolean produtoDisponivel(Produto produto) {
		return produto.getQuantidade() > 0;
	}

	private void finalizarPedido(Pedido pedido) {
		pedido.getItensPedido().forEach(itemPedido -> {
			itemPedido.setPedido(pedido);
			produtoBusiness.retirarDoEstoque(itemPedido);
			itemPedidoDao.adicionar(itemPedido);
		});
	}

}
