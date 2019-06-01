package br.com.escolpi.ecommerce.servlet.logic.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {

	EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("ecommerce-web")
			.createEntityManager();
	
	String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
