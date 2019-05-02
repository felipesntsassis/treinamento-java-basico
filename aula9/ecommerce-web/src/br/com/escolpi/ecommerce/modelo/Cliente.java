package br.com.escolpi.ecommerce.modelo;

import java.util.Calendar;

public class Cliente extends Pessoa {

	private String endereco;
	private Calendar dataNascimento;

	public Cliente() { }

	public Cliente(Long id) {
		this.setId(id);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
