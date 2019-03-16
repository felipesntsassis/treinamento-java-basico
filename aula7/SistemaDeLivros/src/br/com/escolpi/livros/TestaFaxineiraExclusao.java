package br.com.escolpi.livros;

import br.com.escolpi.livros.business.FaxineiraBusiness;
import br.com.escolpi.livros.modelo.rh.Faxineira;

public class TestaFaxineiraExclusao {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		FaxineiraBusiness faxineiraBusiness = new FaxineiraBusiness();
		Faxineira faxineira = faxineiraBusiness.obter(1000);
	
		if (faxineira != null) {
			faxineiraBusiness.excluir(faxineira.getId());
		}

		System.out.println("\n====================================================================================\n");
	}

}
