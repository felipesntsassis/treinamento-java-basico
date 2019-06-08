package br.com.escolpi.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
@NamedQuery(name = "findAll", query = "SELECT d FROM Departamento d")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public Departamento() {
	}
	
	public Departamento(Long id) {
		this.id = id;
	}

	public Departamento(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
