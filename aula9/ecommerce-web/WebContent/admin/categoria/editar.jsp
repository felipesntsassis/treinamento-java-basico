<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="edicao" value="${not empty categoria.id and categoria.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Categoria</h3>
<form action="/ecommerce-web/mvc?logica=SalvarCategoria" method="POST" autocomplete="off">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${categoria.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12 col-lg-6 form-group">
			<label for="txt-descricao" class="required">Descrição:</label>
			<input type="text" id="txt-descricao" name="descricao" value="${categoria.descricao}" 
				class="form-control" size="50" maxlength="100" required autofocus>
		</div>
	</div>
	<button type="button" class="btn btn-outline-secondary mr-2" 
		onclick="irPara('/ecommerce-web/mvc?logica=ListarCategoria');" tabindex="-1">
		<i class="fa fa-reply"></i> Voltar
	</button>
	<button type="submit" class="btn btn-success">
		<i class="fa fa-save"></i> Salvar
	</button>
</form>

<c:import url="/rodape.jsp"/>
