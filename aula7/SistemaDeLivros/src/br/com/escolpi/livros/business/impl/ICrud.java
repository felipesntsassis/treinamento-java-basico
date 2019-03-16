package br.com.escolpi.livros.business.impl;

import java.io.File;
import java.io.IOException;

public interface ICrud<T> {

	final int ID_INICIAL = 1000;

	// C - create/criar
	T incluir(T funcionario);

	// R - read/ler
	T obter(int id);

	T[] listar();

	// U - update/atualizar
	T alterar(T funcionario);

	// D - delete/excluir
	void excluir(int id);

	// Método para obter o data source da classe
	File getDataSource(String datasource) throws IOException;

	int proximoId();
}
