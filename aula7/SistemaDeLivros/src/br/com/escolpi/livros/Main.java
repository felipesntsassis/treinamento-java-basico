package br.com.escolpi.livros;

import br.com.escolpi.livros.business.VendedorBusiness;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Vendedor;

public class Main {

	public static void main(String[] args) {
		VendedorBusiness vendedorBusiness = new VendedorBusiness();
		Vendedor vendedor = new Vendedor();
		vendedor.recebeDados("Felipe Assis", "335.560.268-05");
		vendedor.contrataFuncionario(new Data(14, 1, 2019), 1900.00);
		vendedorBusiness.incluir(vendedor);

		Vendedor vendedor2 = new Vendedor();
		vendedor2.recebeDados("Suellen Assis", "051.078.129-29");
		vendedor2.contrataFuncionario(new Data(7, 10, 2009), 1500.00);
		vendedorBusiness.incluir(vendedor2);
		
		Vendedor[] vendedores = vendedorBusiness.listar();
		
//		for (int i = 0; i < vendedores.length; i++) {
//			vendedores[i].exibeDados();
//		}
//		O foreach abaixo, é uma forma resumida de exibir o for.
		for (Vendedor vend : vendedores) {
			vend.exibeDados();
		}
	}

}
