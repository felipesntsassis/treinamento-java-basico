package br.com.escolpi.aula8.list;

import java.util.ArrayList;
import java.util.List;

public class TestaArrayList {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<String>();
		lista.add("Maria");
		lista.add("Joaquim");
		lista.add("Manoel");
//		Quando temos coleções tipadas, quando tentamos incluir outros tipos de objeto
//		Ocorrem erros de compilação.
//		lista.add(1);
//		lista.add(5.23D);
//		lista.add(new BigDecimal(125000.35));
		
		System.out.println("Minha lista tem " + lista.size() + " elementos");
		
		for (Object item : lista) {
			System.out.println(item.toString());
		}
	}

}
