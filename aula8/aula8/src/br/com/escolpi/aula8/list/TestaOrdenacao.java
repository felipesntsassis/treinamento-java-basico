package br.com.escolpi.aula8.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.escolpi.aula8.modelo.ContaCorrente;

public class TestaOrdenacao {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		lista.addAll(Arrays.asList("Vinicius", "Junio", "Felipe"));

		Collections.sort(lista);
		System.out.println(lista);

		List<ContaCorrente> contas = new ArrayList<>();
		ContaCorrente conta1 = new ContaCorrente();
		conta1.depositar(500);

		ContaCorrente conta2 = new ContaCorrente();
		conta2.depositar(300);
		
		ContaCorrente conta3 = new ContaCorrente();
		conta3.depositar(100);
		
		contas.addAll(Arrays.asList(conta1, conta2, conta3));

		Collections.sort(contas);
		System.out.println(contas);
	}

}
