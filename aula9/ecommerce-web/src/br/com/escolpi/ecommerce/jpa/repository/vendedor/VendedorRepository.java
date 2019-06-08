package br.com.escolpi.ecommerce.jpa.repository.vendedor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.escolpi.ecommerce.modelo.IVendedorRepository;
import br.com.escolpi.ecommerce.modelo.Vendedor;

public class VendedorRepository implements IVendedorRepository {

	private EntityManager em;

	public VendedorRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void excluir(Vendedor vendedor) {
		em.getTransaction().begin();

		if (em.contains(vendedor)) {
			em.remove(vendedor);
		} else {
			em.merge(vendedor);
		}

		em.getTransaction().commit();
	}

	@Override
	public List<Vendedor> listarTodos() {
		TypedQuery<Vendedor> query = em.createQuery("FROM Vendedor", Vendedor.class);

		return query.getResultList();
	}

	@Override
	public Vendedor obterPorId(Long id) {
		return em.find(Vendedor.class, id);
	}

	@Override
	public Vendedor salvar(Vendedor vendedor) {
		em.getTransaction().begin();

		if (vendedor.getId() == null) {
			em.persist(vendedor);
		} else {
			em.merge(vendedor);
		}

		em.getTransaction().commit();

		return vendedor;
	}

}
