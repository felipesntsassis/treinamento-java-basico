package br.com.escolpi.ecommerce.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.escolpi.ecommerce.modelo.Departamento;

public class GerarTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-web");
		EntityManager manager = factory.createEntityManager();
		
		Departamento departamento = new Departamento();
		departamento.setDescricao("Eletrônicos");
		manager.getTransaction().begin();
		manager.persist(departamento);
		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}
