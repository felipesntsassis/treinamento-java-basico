package br.com.escolpi.aula8.list;

import java.util.ArrayList;
import java.util.List;

import br.com.escolpi.aula8.modelo.ContaCorrente;

public class TestaListaContaCorrente {

	public static void main(String[] args) {
		List<ContaCorrente> contas = new ArrayList<>();

		ContaCorrente conta1 = new ContaCorrente();
		conta1.depositar(500);

		ContaCorrente conta2 = new ContaCorrente();
		conta2.depositar(100);

		ContaCorrente conta3 = new ContaCorrente();
		conta3.depositar(250);

		contas.add(conta1);
		contas.add(conta2);
		contas.add(conta3);

		System.out.println(contas.size());

		for (int i = 0; i < contas.size(); i++) {
			ContaCorrente cc = contas.get(i);
			System.out.println(cc.getSaldo());
		}

		contas.remove(1);
		System.out.println(contas.size());

		for (ContaCorrente cc : contas) {
			System.out.println(cc.getSaldo());
		}
		
		contas.clear();
		System.out.println(contas.size());
	}

}
