package br.com.escolpi.livros;

import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.produto.Livro;
import br.com.escolpi.livros.modelo.rh.Faxineira;
import br.com.escolpi.livros.modelo.rh.Vendedor;
import br.com.escolpi.livros.util.Util;

public class Principal {

	public static void main(String[] args) {
		Vendedor brazCubas = new Vendedor();
		brazCubas.recebeDados("Braz Cubas", "335.560.268-25");
		brazCubas.contrataFuncionario(new Data(15, 05, 2017), 1800);
		brazCubas.exibeDados();
		
		Faxineira mariaPereira = new Faxineira();
		mariaPereira.recebeDados("Maria Pereira", "051.078.129-29");
		mariaPereira.contrataFuncionario(new Data(25, 06, 2000), 1500);
		mariaPereira.exibeDados();
		
		Vendedor[] vendedores = new Vendedor[3];
		vendedores = cadastraEListaVendedores(vendedores);
		
		Livro[] estante = new Livro[10];
		estante = cadastraEListaLivros(estante);
		
		vendedores[0].vender(estante[5], estante);
	}

	public static Vendedor[] cadastraEListaVendedores(Vendedor[] vendedores) {
		for (int i = 0; i < vendedores.length; i++) {
			Vendedor vendedor = new Vendedor();
			vendedor.recebeDados("Vendedor Exemplo " + i, "999.999.999-99");
			vendedor.contrataFuncionario(new Data(
					Util.getNumeroAleatorio(31),
					Util.getNumeroAleatorio(12),
					Util.getNumeroAleatorio(2019)),
					2000.00);
			vendedor.exibeDados();
			vendedores[i] = vendedor;
		}

		return vendedores;
	}

	public static Livro[] cadastraEListaLivros(Livro[] livros) {
		for (int i = 0; i < livros.length; i++) {
			Livro livro = new Livro();
			livro.setDescricao("Livro Exemplo " + i);
			livro.setGenero("Aventura");
			livro.setIsbn("132456-" + i );
			livro.setTitulo("Título do Livro " + i);
			livro.exibeDados();
			livros[i] = livro;
		}

		return livros;
	}
}
