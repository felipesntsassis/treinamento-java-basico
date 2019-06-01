package br.com.escolpi.ecommerce.modelo;

public class Categoria {

	private Long id;
	private String descricao;

	public Categoria() {
	}

	public Categoria(String descricao) {
		this.descricao = descricao;
	}

	public Categoria(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
