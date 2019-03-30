package br.com.escolpi.ecommerce.business;

import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.modelo.Cliente;

public class ClienteBusiness {

	private ClienteDao dao = new ClienteDao();

	public Cliente obter(Long id) {
		return dao.obter(id);
	}

}
