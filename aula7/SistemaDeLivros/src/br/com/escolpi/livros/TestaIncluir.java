package br.com.escolpi.livros;

import br.com.escolpi.livros.business.VendedorBusiness;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Vendedor;

public class TestaIncluir {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		VendedorBusiness vendedorBusiness = new VendedorBusiness();
		Vendedor vendedor = new Vendedor();
		vendedor.recebeDados("Felipe Assis", "335.560.268-05");
		vendedor.contrataFuncionario(new Data(14, 1, 2019), 1900.00);
		vendedorBusiness.incluir(vendedor);

		Vendedor vendedor2 = new Vendedor();
		vendedor2.recebeDados("Suellen Assis", "051.078.129-29");
		vendedor2.contrataFuncionario(new Data(7, 10, 2009), 1500.00);
		vendedorBusiness.incluir(vendedor2);
		
		Vendedor vendedor3 = new Vendedor();
		vendedor3.recebeDados("Isabelle Assis", "293.095.910-05");
		vendedor3.contrataFuncionario(new Data(7, 10, 2009), 1500.00);
		vendedorBusiness.incluir(vendedor3);

		System.out.println("\n====================================================================================\n");
	}

}
