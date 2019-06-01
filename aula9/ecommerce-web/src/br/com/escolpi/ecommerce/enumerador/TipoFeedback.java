package br.com.escolpi.ecommerce.enumerador;

public enum TipoFeedback {
	ALERTA ("warning"),
	AVISO ("info"),
	ERRO ("danger"),
	SUCESSO ("success");

	private TipoFeedback(String estilo) {
		this.estilo = estilo;
	}

	private String estilo;

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

}
