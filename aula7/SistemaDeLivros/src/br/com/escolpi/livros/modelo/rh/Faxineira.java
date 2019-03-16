package br.com.escolpi.livros.modelo.rh;

import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.impl.IFaxineira;
import br.com.escolpi.livros.util.Mensagem;

public class Faxineira extends Funcionario implements IFaxineira {

	@Override
	public void calculaSalario(double salario) {
		this.salario = salario + (salario * 0.15);
	}

	@Override
	public void exibeDados() {
		super.exibeDados();
		System.out.println(Mensagem.getConsulta(Mensagem.consulta, "Faxineira"));
	}

	@Override
	public void contrataFuncionario(Data dataAdmissao, double salario) {
		this.dataAdmissao = dataAdmissao;
		calculaSalario(salario);
		System.out.println(Mensagem.getAlteracao("Faxineira"));
	}

	@Override
	public void limparLoja() {
		System.out.println(nome + " limpou a loja!");
	}

	@Override
	public void organizarEstantes() {
		System.out.println(nome + " organizou as estantes!");
	}

	@Override
	public void fazerCafe() {
		System.out.println(nome + " fez um delicioso café!");
	}

}
