package br.com.escolpi.ecommerce.jdbc.dao;

import java.util.List;

public interface Dao<T> {

	public void adicionar(T entidade);

	public void alterar(T entidade);

	public List<T> listar();

	public T obter(Long id);

	public void remover(Long id);

}
