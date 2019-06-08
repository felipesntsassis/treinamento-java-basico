package br.com.escolpi.ecommerce.jpa.exemplos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.escolpi.ecommerce.modelo.Departamento;

public class TestaConsulta {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-web");
		EntityManager manager = factory.createEntityManager();
		List<Departamento> novosDepartamentos = new ArrayList<Departamento>();
		novosDepartamentos.add(new Departamento("Games"));
		novosDepartamentos.add(new Departamento("Eletrodomésticos"));
		novosDepartamentos.add(new Departamento("Moda"));
		novosDepartamentos.add(new Departamento("Moda Infantil"));
		novosDepartamentos.add(new Departamento("Cozinha, Mesa e Banho"));
		novosDepartamentos.add(new Departamento("Artigos para Festas"));

//		manager.getTransaction().begin();
//		novosDepartamentos.forEach(departamento -> manager.persist(departamento));
//		manager.getTransaction().commit();

//		Query query = manager.createQuery("SELECT d FROM Departamento d");
		Query query = manager.createNamedQuery("findAll");
		List<Departamento> resultado = query.getResultList();
		resultado.forEach(departamento -> System.out.println(departamento.getDescricao()));
		manager.close();
		factory.close();
	}

}
