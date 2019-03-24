package br.com.escolpi.ecommerce.jdbc;

import java.util.Locale;

import com.github.javafaker.Faker;

public interface TestaCrud {

	final static int TOTAL_REGISTROS = 10;
	final static Faker faker = new Faker(new Locale("pt-BR"));

	void criar();

	void obter();

	void alterar();

	void remover();

	void listar();

}
