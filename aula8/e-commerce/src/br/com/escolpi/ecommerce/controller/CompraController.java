package br.com.escolpi.ecommerce.controller;

import br.com.escolpi.ecommerce.business.ClienteBusiness;
import br.com.escolpi.ecommerce.business.CompraBusiness;
import br.com.escolpi.ecommerce.business.ProdutoBusiness;
import br.com.escolpi.ecommerce.business.VendedorBusiness;
import br.com.escolpi.ecommerce.modelo.Cliente;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.modelo.Vendedor;

public class CompraController {

	public static CompraBusiness business = new CompraBusiness();
	public static ClienteBusiness clienteBusiness = new ClienteBusiness();
	public static ProdutoBusiness produtoBusiness = new ProdutoBusiness();
	public static VendedorBusiness vendedorBusiness = new VendedorBusiness();

	public static void main(String[] args) {
//		escolherProdutos();
//		concluirCompra();
		comprarProdutoEmFalta();
//		comprarProdutoAcimaDoEstque();
	}

	public static void escolherProdutos() {
		Produto produto1 = produtoBusiness.obter(27L);
		Produto produto2 = produtoBusiness.obter(35L);
		Produto produto3 = produtoBusiness.obter(39L);

		try {
			business.adicionarNoCarrinho(produto1, 1);
			business.adicionarNoCarrinho(produto2, 1);
			business.adicionarNoCarrinho(produto3, 1);
		} catch (Exception e) {
			
		}
	}

	private static void concluirCompra() {
		Vendedor vendedor = vendedorBusiness.obter(1L);
		Cliente cliente = clienteBusiness.obter(1L);
		business.comprarProduto(vendedor, cliente);
		business.limparCarrinho();
	}

	public static void comprarProdutoEmFalta() {
		try {
			Vendedor vendedor = vendedorBusiness.obter(2L);
			Cliente cliente = clienteBusiness.obter(1L);
			Produto liquidificador = produtoBusiness.obter(30L);
			business.adicionarNoCarrinho(liquidificador, 2);
			business.comprarProduto(vendedor, cliente);
			business.limparCarrinho();
		} catch (Exception e) {
			System.out.println("Não foi possível escolher este produto: " + e.getMessage());
		}
	}
	
	public static void comprarProdutoAcimaDoEstque() {
		try {
			Vendedor vendedor = vendedorBusiness.obter(2L);
			Cliente cliente = clienteBusiness.obter(1L);
			Produto livro = produtoBusiness.obter(28L);
			business.adicionarNoCarrinho(livro, 6);
			business.comprarProduto(vendedor, cliente);
			business.limparCarrinho();
		} catch (Exception e) {
			System.out.println("Não foi possível escolher este produto: " + e.getMessage());
		}
	}

}
