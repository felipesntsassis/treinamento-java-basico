<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="escolpi" %>

<c:set var="edicao" value="${not empty endereco.id && endereco.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Endereco</h3>
<form action="/ecommerce-web/mvc?logica=SalvarEndereco" method="POST" autocomplete="off">
	<input type="hidden" name="clienteId" value="${endereco.clienteId}">
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<escolpi:endereco endereco="${endereco}"/>
	<div class="row">
		<div class="form-group col-12">
			<div class="custom-control custom-checkbox">
				<input type="checkbox" id="cb-endereco-principal" name="enderecoPrincipal" class="custom-control-input"
					${endereco.enderecoPrincipal ? "checked" : ""}>
				<label class="custom-control-label" for="cb-endereco-principal">
					Definir como endereço principal
				</label>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-outline-secondary mr-2" 
		onclick="irPara('/ecommerce-web/mvc?logica=ListarEndereco&clienteId=${endereco.clienteId}');" tabindex="-1">
		<i class="fa fa-reply"></i> Voltar
	</button>
	<button type="submit" class="btn btn-success">
		<i class="fa fa-save"></i> Salvar
	</button>
</form>

<c:import url="/rodape.jsp"/>
