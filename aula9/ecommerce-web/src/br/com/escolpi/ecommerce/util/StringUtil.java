package br.com.escolpi.ecommerce.util;

import javax.swing.text.MaskFormatter;

public class StringUtil {

	public static String removerCaracteresEspeciais(String valor) {
		if (isBlank(valor))
			return "";
		return valor.replaceAll("[^a-zA-Z0-9]+", "");
	}

	public static boolean isBlank(String valor) {
		return valor == null || valor.trim().equals("");
	}

	public static String formatar(String pattern, String valor) {
		try {
			if (isBlank(valor))
				return "";

			MaskFormatter mascara = new MaskFormatter(pattern);
			mascara.setValueContainsLiteralCharacters(false);

			return mascara.valueToString(valor);
		} catch (Exception e) {
			return valor;
		}
	}

	public static String formatarCEP(String valor) {
		return formatar("#####-###", valor);
	}

}
