package br.com.escolpi.ecommerce.modelo;

import java.util.List;

public interface IVendedorRepository {

	void excluir(Vendedor vendedor);

	List<Vendedor> listarTodos();

	Vendedor obterPorId(Long id);

	Vendedor salvar(Vendedor vendedor);

}
