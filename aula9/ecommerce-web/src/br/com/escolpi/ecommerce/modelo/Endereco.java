package br.com.escolpi.ecommerce.modelo;

import br.com.escolpi.ecommerce.enumerador.Estados;

public class Endereco {

	private Long id;
	private Long clienteId;
	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String complemento;
	private Estados estado;
	private String municipio;
	private boolean enderecoPrincipal;
	private String enderecoFormatado;

	public Long getId() {
		return id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public Estados getEstado() {
		return estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public boolean isEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public void setEnderecoPrincipal(boolean enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

}
