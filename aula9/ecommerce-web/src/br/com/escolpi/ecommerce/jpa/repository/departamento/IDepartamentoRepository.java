package br.com.escolpi.ecommerce.jpa.repository.departamento;

import java.util.List;

import br.com.escolpi.ecommerce.modelo.Departamento;

public interface IDepartamentoRepository {

	void excluir(Departamento departamento);

	List<Departamento> listarTodos();

	Departamento obterPorId(Long id);

	Departamento salvar(Departamento departamento);

	boolean departamentoTemVendedores(Long id);
}
