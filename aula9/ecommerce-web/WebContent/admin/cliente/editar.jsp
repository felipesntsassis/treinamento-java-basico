<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="escolpi" %>

<c:set var="edicao" value="${not empty cliente.id && cliente.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Cliente</h3>
<form action="/ecommerce-web/mvc?logica=SalvarCliente" method="POST" autocomplete="off">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${cliente.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-12 col-lg-6">
			<label for="txt-nome" class="required">Nome:</label>
			<input type="text" name="nome" id="txt-nome" class="form-control" value="${cliente.nome}" maxlength="100" 
				required autofocus>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="txt-email" class="required">E-mail:</label>
			<input type="email"name="email" class="form-control" value="${cliente.email}" id="txt-email" 
				maxlength="100" required>
		</div>
	</div>
	<escolpi:endereco endereco="${cliente.endereco}"/>
	<div class="row mb-3">
		<div class="form-group col-12 col-lg-3">
			<label for="txt-data-nascimento" class="required">Data de Nascimento:</label>
			<fmt:formatDate var="dataNascimento" value="${cliente.dataNascimento.time}" 
				pattern="dd/MM/yyyy"/>
			<escolpi:campoData name="dataNascimento" value="${dataNascimento}" 
				id="txt-data-nascimento" required="false"/>
		</div>
	</div>
	<button type="button" class="btn btn-outline-secondary mr-2" 
		onclick="irPara('/ecommerce-web/mvc?logica=ListarCliente');" tabindex="-1">
		<i class="fa fa-reply"></i> Voltar
	</button>
	<button type="submit" class="btn btn-success">
		<i class="fa fa-save"></i> Salvar
	</button>
</form>

<c:import url="/rodape.jsp"/>
