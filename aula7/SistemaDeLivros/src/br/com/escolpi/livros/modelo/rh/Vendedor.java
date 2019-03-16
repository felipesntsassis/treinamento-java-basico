package br.com.escolpi.livros.modelo.rh;

import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.produto.Livro;
import br.com.escolpi.livros.modelo.rh.impl.IVendedor;
import br.com.escolpi.livros.util.Mensagem;

public class Vendedor extends Funcionario implements IVendedor {

	@Override
	public void calculaSalario(double salario) {
		this.salario = salario + (salario * 0.25d);
	}

	@Override
	public void exibeDados() {
		super.exibeDados();
	}

	@Override
	public void contrataFuncionario(Data dataAdmissao, double salario) {
		this.dataAdmissao = dataAdmissao;
		calculaSalario(salario);
	}

	@Override
	public void vender(Livro livroAVender, Livro... estante) {
		for (int i = 0; i < estante.length; i++) {
			if (estante[i].equals(livroAVender)) {
				estante[i] = null;
				break;
			}
		}
		
		System.out.println(Mensagem.getVenda(livroAVender.getTitulo(), nome));
	}

	@Override
	public void relizarTroca(Livro livro) {
		System.out.println("Trocou o livro");
		
	}
}
