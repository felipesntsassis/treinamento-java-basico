package br.com.escolpi.livros.business.impl;

import br.com.escolpi.livros.modelo.rh.Vendedor;

public interface ICrud {

	final int ID_INICIAL = 1000;

	// C - create/criar
	Vendedor incluir(Vendedor funcionario);

	// R - read/ler
	Vendedor obter(int id);

	Vendedor[] listar();

	// U - update/atualizar
	Vendedor alterar(Vendedor funcionario);

	// D - delete/excluir
	void excluir(int id);

	// Método para obter o data source da classe
	String getDataSource(String datasource);

	int proximoId();
}
