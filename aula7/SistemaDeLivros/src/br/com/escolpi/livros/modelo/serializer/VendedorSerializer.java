package br.com.escolpi.livros.modelo.serializer;

import java.lang.reflect.Field;

import br.com.escolpi.livros.modelo.Data;
import br.com.escolpi.livros.modelo.rh.Vendedor;

public class VendedorSerializer {

	public static String vendedorParaTexto(Vendedor vendedor) throws IllegalArgumentException, 
	IllegalAccessException {
		StringBuilder registro = new StringBuilder();
		String template = "%s:%s|";
		
		for (Field field : vendedor.getClass().getSuperclass().getDeclaredFields()) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			registro.append(String.format(template, field.getName(), field.get(vendedor)));
			field.setAccessible(accessible);
		}

		return registro.substring(0, registro.lastIndexOf("|"));
	}

	public static Vendedor textoParaVendedor(String vendedor) throws NumberFormatException, 
		IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String[] tupla = vendedor.split("\\|");
		Vendedor registro = new Vendedor();

		for (String coluna : tupla) {
			String[] campo = coluna.split(":");
			Field field = registro.getClass().getSuperclass().getDeclaredField(campo[0]);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);

			if (field.getType().isAssignableFrom(Data.class)) {
				String[] data = campo[1].split("/");
				field.set(registro, new Data(
						Integer.valueOf(data[0]), 
						Integer.valueOf(data[1]), 
						Integer.valueOf(data[2])
					)
				);
			} else if (field.getType().getName() == "int") {
				field.set(registro, Integer.valueOf(campo[1]));
			} else if (field.getType().getName() == "double") {
				field.set(registro, Double.valueOf(campo[1]));
			} else {
				field.set(registro, campo[1]);
			}

			field.setAccessible(accessible);
		}
		
		return registro;
	}

}
