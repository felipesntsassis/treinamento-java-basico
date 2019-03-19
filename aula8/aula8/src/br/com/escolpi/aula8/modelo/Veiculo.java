package br.com.escolpi.aula8.modelo;

import br.com.escolpi.aula8.enumerador.TipoVeiculo;

public class Veiculo {

	private String modelo;
	private String marca;
	private String cor;
	private String placa;
	private TipoVeiculo tipo;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		return modelo.equals(((Veiculo) obj).modelo);
	}

	
}
