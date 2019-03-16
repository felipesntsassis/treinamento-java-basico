package br.com.escolpi.livros;

import br.com.escolpi.livros.business.FaxineiraBusiness;
import br.com.escolpi.livros.modelo.rh.Faxineira;

public class TestaFaxineiraLista {

	public static void main(String[] args) {
		FaxineiraBusiness business = new FaxineiraBusiness();
		Faxineira[] faxineiras = business.listar();
		
		for (Faxineira faxineira : faxineiras) {
			faxineira.exibeDados();
		}
	}

}
