package br.com.escolpi.ecommerce.jpa.exemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.escolpi.ecommerce.modelo.Departamento;

public class TestaConsultaComFiltro {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-web");
		EntityManager manager = factory.createEntityManager();
		
		String jpql = "FROM Departamento WHERE UPPER(descricao) LIKE :filtro";
		TypedQuery<Departamento> query = manager.createQuery(jpql , Departamento.class);
		query.setParameter("filtro", "moda%".toUpperCase());
		List<Departamento> resultado = query.getResultList();
		
		resultado.forEach(departamento -> System.out.println(departamento.getDescricao()));
		manager.close();
		factory.close();
	}
}
