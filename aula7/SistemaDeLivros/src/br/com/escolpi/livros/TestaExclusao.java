package br.com.escolpi.livros;

import br.com.escolpi.livros.business.VendedorBusiness;
import br.com.escolpi.livros.modelo.rh.Vendedor;

public class TestaExclusao {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		VendedorBusiness vendedorBusiness = new VendedorBusiness();
		Vendedor vendedor = vendedorBusiness.obter(1001);
	
		if (vendedor != null) {
			vendedorBusiness.excluir(vendedor.getId());
		}

		System.out.println("\n====================================================================================\n");
	}

}
