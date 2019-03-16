package br.com.escolpi.livros;

import br.com.escolpi.livros.business.FaxineiraBusiness;
import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Faxineira;

public class TestaFaxineiraInclusao {

	public static void main(String[] args) {
		System.out.println("\n====================================================================================\n");

		FaxineiraBusiness faxineiraBusiness = new FaxineiraBusiness();
		Faxineira faxineira = new Faxineira();
		faxineira.recebeDados("Maria da Silva", "335.560.268-05");
		faxineira.contrataFuncionario(new Data(14, 1, 2019), 1500.00);
		faxineiraBusiness.incluir(faxineira);

		Faxineira faxineira2 = new Faxineira();
		faxineira2.recebeDados("José Antônio da Silva", "051.078.129-29");
		faxineira2.contrataFuncionario(new Data(71, 1, 1991), 1500.00);
		faxineiraBusiness.incluir(faxineira2);

		Faxineira faxineira3 = new Faxineira();
		faxineira3.recebeDados("Machdo de Assis", "293.095.910-05");
		faxineira3.contrataFuncionario(new Data(2, 5, 1696), 1500.00);
		faxineiraBusiness.incluir(faxineira3);

		System.out.println("\n====================================================================================\n");
	}
}
