package br.com.escolpi.ecommerce.util;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

	private static Locale BR = new Locale("pt", "BR");

	public static String formatarPercentual(Number numero, boolean comSimbolo) {
		NumberFormat nf = NumberFormat.getInstance(BR);
		nf.setMaximumIntegerDigits(3);
		nf.setMaximumFractionDigits(2);

		return nf.format(numero) + (comSimbolo ? " %" : "");
	}

}
