package br.com.escolpi.aula8.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.escolpi.aula8.modelo.ContaCorrente;

public class TestaOrdenacaoStream {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		for (int i = 1; i <= 30000; i++) {
			lista.add("Pessoa Exemplo " + i);
		}
		
		long inicio = System.currentTimeMillis();
		long fim = 0;
		long tempoGasto = 0;
//		for (String item : lista) {
//			System.out.println(item);
//		}
//		lista.forEach(pessoa -> {
//			System.out.println(pessoa);
//		});
		List<String> filtroPessoas = lista.stream()
			.filter(pessoa -> pessoa.contains("56"))
			.collect(Collectors.toList());
		
		filtroPessoas.forEach(filtro -> System.out.println(filtro));
		
		fim = System.currentTimeMillis();
		tempoGasto = fim - inicio;
		System.out.println("Tempo Gasto: " + tempoGasto);
		
		List<String> nomes = new ArrayList<>(
			Arrays.asList("Isabelle", "Felipe", "Vinicius", "Suellen", "Paulo")
		);
		
		List<String> nomesOrdenados = nomes.stream()
				.sorted()
				.collect(Collectors.toList());
		System.out.println(nomesOrdenados);
		
		List<ContaCorrente> contas = new ArrayList<>();
		ContaCorrente conta1 = new ContaCorrente();
		conta1.depositar(100);
		
		ContaCorrente conta2 = new ContaCorrente();
		conta2.depositar(50);
		
		ContaCorrente conta3 = new ContaCorrente();
		conta1.depositar(1500);
		
		contas.add(conta1);
		contas.add(conta2);
		contas.add(conta3);
		
		List<ContaCorrente> contasOrdenadas = contas.stream()
				.sorted((c1, c2) -> c1.getSaldo().compareTo(c2.getSaldo()))
				.collect(Collectors.toList());
		System.out.println(contasOrdenadas);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
