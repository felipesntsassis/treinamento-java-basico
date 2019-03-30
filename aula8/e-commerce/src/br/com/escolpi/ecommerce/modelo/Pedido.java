package br.com.escolpi.ecommerce.modelo;

import java.util.Calendar;
import java.util.List;

import br.com.escolpi.ecommerce.enumerador.SituacaoPedido;

public class Pedido {

	private Long id;
	private Vendedor vendedor;
	private Cliente cliente;
	private Calendar dataPedido;
	private SituacaoPedido situacao;
	private List<ItemPedido> itensPedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente clienteId) {
		this.cliente = clienteId;
	}

	public Calendar getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
}
