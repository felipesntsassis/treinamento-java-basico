package br.com.escolpi.livros;

import br.com.escolpi.livros.business.VendedorBusiness;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Vendedor;
import br.com.escolpi.livros.util.Mensagem;

public class TestaAlterar {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		VendedorBusiness vendedorBusiness = new VendedorBusiness();
		Vendedor vendedor = vendedorBusiness.obter(1000);
	
		if (vendedor != null) {
			vendedor.setNome("Felipe dos Santos Assis");
			vendedor.setDataAdmissao(new Data(1, 4, 2009));
			vendedorBusiness.alterar(vendedor);
			System.out.println(Mensagem.getAlteracao("Vendedor"));
		}
		
		System.out.println("\n====================================================================================\n");
	}

}
