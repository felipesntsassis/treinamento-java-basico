package br.com.escolpi.ecommerce.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	public static String parseToString(Calendar data, String pattern) {
		return new SimpleDateFormat(pattern).format(data.getTime());
	}

}
