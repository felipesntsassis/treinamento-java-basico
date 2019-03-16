package br.com.escolpi.livros.modelo.rh;

import br.com.escolpi.livros.modelo.Data;

public abstract class Funcionario {

	protected int id;
	protected String nome;
	protected String cpf;
	protected double salario;
	protected Data dataAdmissao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Data getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Data dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public abstract void calculaSalario(double salario);

	public abstract void contrataFuncionario(Data dataAdmissao, double salario);

	public void recebeDados(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public void exibeDados () {
		System.out.println("\n========================================\n");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Salário: " + salario);
		System.out.println("Data de Admissão: " + dataAdmissao.toString());
	}
}
