package br.com.escolpi.ecommerce.modelo;

public class Vendedor extends Pessoa {

	private String departamento;
	private Double percentualComissao;

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Double getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(Double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

}
