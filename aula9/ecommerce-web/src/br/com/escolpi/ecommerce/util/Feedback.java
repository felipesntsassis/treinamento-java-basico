package br.com.escolpi.ecommerce.util;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;

public class Feedback {

	private TipoFeedback tipoFeedback;
	private String mensagem;

	public Feedback(TipoFeedback tipoFeedback, String mensagem) {
		this.tipoFeedback = tipoFeedback;
		this.mensagem = mensagem;
	}

	public TipoFeedback getTipoFeedback() {
		return tipoFeedback;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setTipoFeedback(TipoFeedback tipoFeedback) {
		this.tipoFeedback = tipoFeedback;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
