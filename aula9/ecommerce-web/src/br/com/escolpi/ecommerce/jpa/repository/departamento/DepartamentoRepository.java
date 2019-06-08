package br.com.escolpi.ecommerce.jpa.repository.departamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.escolpi.ecommerce.modelo.Departamento;

public class DepartamentoRepository implements IDepartamentoRepository {

	private EntityManager em;

	public DepartamentoRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void excluir(Departamento departamento) {
		em.getTransaction().begin();

		if (em.contains(departamento)) {
			em.remove(departamento);
		} else {
			em.merge(departamento);
		}

		em.getTransaction().commit();
	}

	@Override
	public List<Departamento> listarTodos() {
		TypedQuery<Departamento> query = em.createQuery("FROM Departamento", Departamento.class);

		return query.getResultList();
	}

	@Override
	public Departamento obterPorId(Long id) {
		return em.find(Departamento.class, id);
	}

	@Override
	public Departamento salvar(Departamento departamento) {
		em.getTransaction().begin();

		if (departamento.getId() == null) {
			em.persist(departamento);
		} else {
			em.merge(departamento);
		}

		em.getTransaction().commit();

		return departamento;
	}

	@Override
	public boolean departamentoTemVendedores(Long id) {
		StringBuilder jpql = new StringBuilder("SELECT COUNT(v) ")
				.append("FROM Vendedor WHERE v.departamento.id = :id");
		TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
		query.setParameter("id", id);
		
		return query.getSingleResult().longValue() > 0L;
	}

}
