package br.com.escolpi.livros;

import br.com.escolpi.livros.business.VendedorBusiness;
import br.com.escolpi.livros.modelo.rh.Vendedor;

public class TestaListar {

	public static void main(String[] args) {
		VendedorBusiness business = new VendedorBusiness();
		Vendedor[] vendedores = business.listar();
		
		for (Vendedor vendedor : vendedores) {
			vendedor.exibeDados();
		}
	}

}
