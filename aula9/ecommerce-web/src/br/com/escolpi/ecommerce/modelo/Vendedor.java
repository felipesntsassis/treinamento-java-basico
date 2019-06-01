package br.com.escolpi.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vendedores")
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "departamento_id", nullable = false)
	private Departamento departamento;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", nullable = false)
	private String email;


	@Column(name = "perc_comissao", nullable = false)
	private Double percentualComissao;

	public Vendedor() {}

	public Vendedor(Long id) {
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Double getPercentualComissao() {
		return percentualComissao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPercentualComissao(Double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

}
