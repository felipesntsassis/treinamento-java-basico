package br.com.escolpi.aula8.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import br.com.escolpi.aula8.enumerador.TipoVeiculo;
import br.com.escolpi.aula8.modelo.Veiculo;

public class TestaVeiculosMap {

	public static void main(String[] args) {
		Map<TipoVeiculo, Veiculo> veiculos = new HashMap<>();

		Veiculo carro = new Veiculo();
		carro.setModelo("Gol");
		carro.setMarca("Volkswagen ");
		carro.setPlaca("ABC123");
		carro.setCor("Vermelho");
		carro.setTipo(TipoVeiculo.CARRO);
		veiculos.put(TipoVeiculo.CARRO, carro);
		Veiculo moto = new Veiculo();
		moto.setModelo("Fan 150");
		moto.setMarca("Honda");
		moto.setPlaca("DVZ1015");
		moto.setCor("Azul");
		moto.setTipo(TipoVeiculo.MOTOCILETA);
		veiculos.put(TipoVeiculo.MOTOCILETA, moto);

		Veiculo caminhonete = new Veiculo();
		caminhonete.setModelo("S10 ");
		caminhonete.setMarca("Chevrolet");
		caminhonete.setPlaca("CDF1310");
		caminhonete.setCor("Branco");
		caminhonete.setTipo(TipoVeiculo.PICKUP);
		veiculos.put(TipoVeiculo.PICKUP, caminhonete);

		// Listando todas as chaves
		for (TipoVeiculo tipo : veiculos.keySet()) {
			System.out.println(tipo.getDescricao());
		}
		// Listando o primeiro elemento por chave
		for (Veiculo veiculo : veiculos.values()) {
			System.out.println("Tipo: " + veiculo.getTipo().getDescricao() + 
					" - Modelo: " + veiculo.getModelo());
		}

		// Listando os veículos de cada chave
		for (TipoVeiculo tipo : veiculos.keySet()) {
			Veiculo veiculo = veiculos.get(tipo);
			
			System.out.println("==========================================\n");
			System.out.println("Marca:	" + veiculo.getMarca());
			System.out.println("Modelo:	" + veiculo.getModelo());
			System.out.println("Placa:	" + veiculo.getPlaca());
			System.out.println("Tipo:	" + veiculo.getTipo().getDescricao());
		}

		System.out.println("==========================================\n");

		Map<TipoVeiculo, Set<Veiculo>> gruposDeVeiculo = new TreeMap<>();
		gruposDeVeiculo.put(TipoVeiculo.CARRO, new HashSet<>());
		gruposDeVeiculo.get(TipoVeiculo.CARRO).add(carro);
		Veiculo carro2 = new Veiculo();
		carro2.setMarca("Chevrolet");
		carro2.setModelo("Onix");
		carro2.setCor("Azul");
		carro2.setPlaca("FET1515");
		carro2.setTipo(TipoVeiculo.CARRO);
		gruposDeVeiculo.get(TipoVeiculo.CARRO).add(carro2);
		gruposDeVeiculo.put(TipoVeiculo.MOTOCILETA, new HashSet<>());
		gruposDeVeiculo.get(TipoVeiculo.MOTOCILETA).add(moto);
		gruposDeVeiculo.put(TipoVeiculo.PICKUP, new HashSet<>());
		gruposDeVeiculo.get(TipoVeiculo.PICKUP).add(caminhonete);
		
		gruposDeVeiculo.forEach((chave, valor) -> {
			System.out.println("\n" + chave.getDescricao() + "\n");
			valor.forEach(veiculo -> {
				System.out.println("Marca:	" + veiculo.getMarca());
				System.out.println("Modelo:	" + veiculo.getModelo());
				System.out.println("Placa:	" + veiculo.getPlaca());
				System.out.println("Tipo:	" + veiculo.getTipo().getDescricao());
			});
		});
	}
	
	
}
