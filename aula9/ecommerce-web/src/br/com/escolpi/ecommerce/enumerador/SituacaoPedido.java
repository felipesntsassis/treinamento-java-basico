package br.com.escolpi.ecommerce.enumerador;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SituacaoPedido {
	NOVO ("Novo"),
	AGUARDANDO_APROVACAO ("Aguardando Aprovação"),
	APROVADO ("Aprovado"),
	FINALIZADO ("Finalizado"),
	CANCELADO ("Cancelado");
	
	private String descricao;
	
	private SituacaoPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<SituacaoPedido> listar() {
		return Arrays.asList(SituacaoPedido.values());
	}

	public static List<SituacaoPedido> listarNaoCancelados() {
		return listar().stream()
				.filter(situacao -> !situacao.equals(SituacaoPedido.CANCELADO))
				.collect(Collectors.toList());
	}

	public static SituacaoPedido obter(int ordinal) {
		// Como fazemos a partir do Java 8
		return Arrays.asList(SituacaoPedido.values()).stream()
			.filter(situacao -> ordinal == situacao.ordinal())
			.findFirst()
			.orElse(null);
//		Como faziamos no Java 7 e versões anteriores
//		for (SituacaoPedido situacao : SituacaoPedido.values()) {
//			if (ordinal == situacao.ordinal()) {
//				return situacao;
//			}
//		}
//		
//		return null;
	}

}
