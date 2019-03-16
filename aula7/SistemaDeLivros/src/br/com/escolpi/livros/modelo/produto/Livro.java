package br.com.escolpi.livros.modelo.produto;

import br.com.escolpi.livros.util.Mensagem;

public class Livro {

	private String titulo;
	private String descricao;
	private String genero;
	private String isbn;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void exibeDados() {
		System.out.println("\n====================\n");
		System.out.println("Titulo: " + titulo);
		System.out.println("Descrição: " + descricao);
		System.out.println("Gênero: " + genero);
		System.out.println("ISBN: " + isbn);
		System.out.println(Mensagem.getConsulta(Mensagem.consulta, "Livro"));
	}

}
