package br.com.escolpi.livros;

import br.com.escolpi.livros.business.FaxineiraBusiness;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Faxineira;
import br.com.escolpi.livros.util.Mensagem;

public class TestaFaxineiraAlteracao {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		FaxineiraBusiness faxineiraBusiness = new FaxineiraBusiness();
		Faxineira faxineira = faxineiraBusiness.obter(1002);

		if (faxineira != null) {
			faxineira.setNome("Maria Pereira");
			faxineira.setDataAdmissao(new Data(15, 8, 2009));
			faxineiraBusiness.alterar(faxineira);
			System.out.println(Mensagem.getAlteracao("Faxineira"));
		}

		System.out.println("\n====================================================================================\n");
	}

}
