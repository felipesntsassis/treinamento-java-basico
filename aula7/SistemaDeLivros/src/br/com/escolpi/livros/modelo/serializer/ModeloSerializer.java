package br.com.escolpi.livros.modelo.serializer;

import java.lang.reflect.Field;

import br.com.escolpi.livros.modelo.Data;

public class ModeloSerializer<T> {

	public String modeloParaTexto(T modelo) throws IllegalArgumentException, 
	IllegalAccessException {
		StringBuilder registro = new StringBuilder();
		String template = "%s:%s|";
		
		for (Field field : modelo.getClass().getSuperclass().getDeclaredFields()) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			registro.append(String.format(template, field.getName(), field.get(modelo)));
			field.setAccessible(accessible);
		}

		return registro.substring(0, registro.lastIndexOf("|"));
	}

	public T textoParaModelo(String modelo, T clazz) throws NoSuchFieldException, 
		SecurityException, NumberFormatException, IllegalArgumentException, IllegalAccessException {
		String[] tupla = modelo.split("\\|");

		for (String coluna : tupla) {
			String[] campo = coluna.split(":");
			Field field = clazz.getClass().getSuperclass().getDeclaredField(campo[0]);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);

			if (field.getType().isAssignableFrom(Data.class)) {
				String[] data = campo[1].split("/");
				field.set(clazz, new Data(
						Integer.valueOf(data[0]), 
						Integer.valueOf(data[1]), 
						Integer.valueOf(data[2])
					)
				);
			} else if (field.getType().getName() == "int") {
				field.set(clazz, Integer.valueOf(campo[1]));
			} else if (field.getType().getName() == "double") {
				field.set(clazz, Double.valueOf(campo[1]));
			} else {
				field.set(clazz, campo[1]);
			}

			field.setAccessible(accessible);
		}

		return clazz;
	}

}
