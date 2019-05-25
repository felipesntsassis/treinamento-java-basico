package br.com.escolpi.ecommerce.modelo;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Pessoa {

	private Calendar dataNascimento;
	private Set<Endereco> enderecosDeEntrega;

	public Cliente() { }

	public Cliente(Long id) {
		this.setId(id);
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<Endereco> getEnderecosDeEntrega() {
		return enderecosDeEntrega;
	}

	public void setEnderecosDeEntrega(Set<Endereco> enderecosDeEntrega) {
		this.enderecosDeEntrega = enderecosDeEntrega;
	}

	public Endereco getEndereco() {
		if (enderecosDeEntrega == null) {
			enderecosDeEntrega = new HashSet<>();
			enderecosDeEntrega.add(new Endereco());
		}

		return enderecosDeEntrega.stream()
				.filter(endereco -> endereco.isEnderecoPrincipal())
				.findFirst()
				.get();
	}
}
