package br.com.escolpi.ecommerce.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String parseToString(Calendar data, String pattern) {
		return new SimpleDateFormat(pattern).format(data.getTime());
	}

	public static Date parseToDate(String data, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(data);
		} catch (ParseException e) {
			System.out.println("Data inválida: " + e.getMessage());
		}
		
		return null;
	}


	public static Calendar parseToCalendar(String data, String pattern) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat(pattern).parse(data));
			return cal;
		} catch (ParseException e) {
			System.out.println("Data inválida: " + e.getMessage());
		}
		
		return null;
	}

}
