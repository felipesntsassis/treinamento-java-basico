package br.com.escolpi.ecommerce.jpa.exemplos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.escolpi.ecommerce.modelo.Departamento;

public class TestaAlteracao {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-web");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		Departamento departamento = manager.find(Departamento.class, 1L);
		departamento.setDescricao("Eletrônicos e Games");
		manager.merge(departamento);
		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}

}
