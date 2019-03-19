package br.com.escolpi.aula8.enumerador;

public enum TipoVeiculo {

	CARRO ("Carro"),
	MOTOCILETA ("Motocicleta"),
	PICKUP ("Pickup"),
	CAMINHAO ("Caminhão");
	
	private TipoVeiculo(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
