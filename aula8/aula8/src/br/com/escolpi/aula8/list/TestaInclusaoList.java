package br.com.escolpi.aula8.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.escolpi.aula8.modelo.Pessoa;

public class TestaInclusaoList {

	public static void main(String[] args) {
		Pessoa felipe = new Pessoa("Felipe", 34);
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(felipe);
		pessoas.add(new Pessoa("Suellen", 33));
		Collections.addAll(pessoas, new Pessoa("Isabelle", 4), 
				new Pessoa("Agenor", 61), new Pessoa("Célia", 58), 
				new Pessoa("Camila", 30));
		pessoas.addAll(Arrays.asList(new Pessoa("Pedro", 0), new Pessoa("Vitor", 32)));
		String saudacao = "Olá! Sou o(a) %s e tenho %s de idade.";

		for (Pessoa pessoa : pessoas) {
			System.out.println(String.format(saudacao, pessoa.getNome(), pessoa.getIdade()));
		}
	}
}
