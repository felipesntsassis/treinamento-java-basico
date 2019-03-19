package br.com.escolpi.aula8.modelo;

public class ContaCorrente implements Comparable<ContaCorrente> {

	private Double saldo = 500.50D;

	public Double getSaldo() {
		return saldo;
	}

	public void depositar(double valor) {
		saldo = saldo + valor;
	}

	@Override
	public String toString() {
		return saldo + "";
	}

	@Override
	public int compareTo(ContaCorrente outraConta) {
		return Double.compare(this.saldo, outraConta.saldo);
//		if (this.saldo < outraConta.saldo) {
//			return -1;
//		}
//		if (this.saldo > outraConta.saldo) {
//			return 1;
//		}
//
//		return 0;
	}
	
}
