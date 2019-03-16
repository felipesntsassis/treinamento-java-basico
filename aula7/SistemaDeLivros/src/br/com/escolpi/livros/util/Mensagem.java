package br.com.escolpi.livros.util;

public class Mensagem {

	public static final String consulta = "%s consultado(a) com sucesso!";
	public static final String exclusao = "%s excluído(a) com sucesso!";
	public static final String alteracao = "%s alterado(a) com sucesso!";
	public static final String inclusao = "%s incluído(a) com sucesso!";
	public static final String venda = "%s foi vendido por %s com sucesso!";
	public static final String erro = "Ocorreu um erro: %s";

	private static String getMensagem(String msg, String... params) {
		String mensagem = String.format(msg, params);

		return mensagem;
	}
	
	public static String getAlteracao(String... params) {
		return getMensagem(alteracao, params);
	}

	public static String getErro(String... params) {
		return getMensagem(erro, params);
	}

	public static String getExclusao(String... params) {
		return getMensagem(exclusao, params);
	}

	public static String getInclusao(String... params) {
		return getMensagem(inclusao, params);
	}

	public static String getConsulta(String... params) {
		return getMensagem(consulta, params);
	}

	public static String getVenda(String... params) {
		return getMensagem(venda, params);
	}

}
