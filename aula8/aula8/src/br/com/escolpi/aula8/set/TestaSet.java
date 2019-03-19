package br.com.escolpi.aula8.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestaSet {

	public static void main(String[] args) {
		Set<String> cargos = new TreeSet<>();
		cargos.add("Gerente");
		cargos.add("Diretor");
		cargos.add("Presidente");
		cargos.add("Secretária");
		cargos.add("Funcionário");
		cargos.add("Diretor");
		
		cargos.forEach(cargo -> System.out.println(cargo));
		
		Set<String> conjunto = new LinkedHashSet<>();
		conjunto.add("item 1");
		conjunto.add("item 2");
		conjunto.add("item 3");
		
		Iterator<String> it = conjunto.iterator();
		while (it.hasNext()) {
			String item = it.next();
			System.out.println(item);
		}
	}

	
}
