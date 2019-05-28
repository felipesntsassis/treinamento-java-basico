package br.com.escolpi.ecommerce.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtil {

	private NumberUtil() {}

	private static Locale localeBr = new Locale("pt", "BR");

	public static String formatarPercentual(Number numero, boolean comSimbolo) {
		NumberFormat nf = DecimalFormat.getInstance(localeBr);
		nf.setMaximumIntegerDigits(3);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);

		return nf.format(numero) + (comSimbolo ? " %" : "");
	}

	public static String formatarMoeda(Number numero, boolean comSimbolo) {
		NumberFormat nf = DecimalFormat.getInstance(localeBr);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);

		return (comSimbolo ? "R$ " : "") + nf.format(numero);
	}

	public static Double parseDouble(String numero) {
		try {
			return DecimalFormat.getNumberInstance().parse(numero).doubleValue();
		} catch (ParseException e) {
			return 0d;
		}
	}

	public static boolean isNullOrZero(Number valor) {
		return valor == null || valor.equals(0);
	}

}
