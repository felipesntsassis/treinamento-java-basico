package br.com.escolpi.ecommerce.jdbc;

import java.util.Locale;

import com.github.javafaker.Faker;

public interface TestaCrud {

	final static int TOTAL_REGISTROS = 10;
	final static Faker faker = new Faker(new Locale("pt-BR"));

	public void criar();

	public void obter();

	public void alterar();

	public void remover();

	public void listar();

}
