package br.com.escolpi.ecommerce.jdbc.dao;

import java.sql.Connection;

import br.com.escolpi.ecommerce.jdbc.ConnectionFactory;

public abstract class GenericDao<T> implements Dao<T> {

	protected Connection connection;

	public GenericDao() {
		connection = ConnectionFactory.getConnection();
	}

}
