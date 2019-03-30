package br.com.escolpi.ecommerce.business;

import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;

public class VendedorBusiness {

	VendedorDao dao = new VendedorDao();

	public Vendedor obter(Long id) {
		return dao.obter(id);
	}

}
