package br.com.escolpi.ecommerce.util;

import java.util.Set;

/**
 * Classe definida para implementar um componente de Data Table padrão do banco de dados
 * @author Felipe dos Santos Assis <felipesntsassis@gmail.com>
 * @param <T>
 */
public class DataTable<T> {

	private T entity;
	private Set<OptionMenu> menu;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Set<OptionMenu> getMenu() {
		return menu;
	}

	public void setMenu(Set<OptionMenu> menu) {
		this.menu = menu;
	}

}
