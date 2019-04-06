package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	void adicionar(T entidade);

	void alterar(T entidade);

	List<T> listar();

	T obter(Long id);

	void remover(Long id);

	T popularEntidade(ResultSet rs) throws SQLException;

}
