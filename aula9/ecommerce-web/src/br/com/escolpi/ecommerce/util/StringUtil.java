package br.com.escolpi.ecommerce.util;

public class StringUtil {

	public static String removerCaracteresEspeciais(String valor) {
		if (isBlank(valor))
			return "";
		return valor.replaceAll("[^a-zA-Z0-9]+", "");
	}

	public static boolean isBlank(String valor) {
		return valor == null || valor == "";
	}

}
