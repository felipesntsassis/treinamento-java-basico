<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="escolpi" %>

<c:set var="edicao" value="${not empty param.id and param.id > 0 }"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? 'Editar' : 'Cadastrar'} Produto</h3>
<form action="/ecommerce-web/mvc?logica=SalvarProduto" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${produto.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<div class="row mb-3">
		<div class="form-group col-12 col-lg-6">
			<label for="txt-descricao" class="required">Descrição:</label>
			<input type="text" id="txt-descricao" name="descricao" class="form-control" value="${produto.descricao}" 
				size="50" maxlength="100" required autofocus>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="combo-categoria" class="required">Categoria:</label>
			<escolpi:comboBox id="combo-categoria" name="categoria" items="${categorias}" entity="${produto.categoria}"/>
		</div>
		<div class="form-group col-12 col-lg-3">
			<label for="txt-quantidade" class="required">Quantidade:</label>
			<input type="number" id="txt-quantidade" name="quantidade" class="form-control" value="${produto.quantidade}" 
				size="10" maxlength="3" min="0" max="100" step="1" required>
		</div>
		<div class="form-group col-12 col-lg-3">
			<label for="txt-preco" class="required">Preço:</label>
			<div class="input-group">
				<div class="input-group-prepend">
					<div class="input-group-text">R$</div>
				</div>
				<fmt:formatNumber var="preco" value="${produto.preco}" type="number"
					minFractionDigits="2" maxFractionDigits="2"/>
				<input type="text" id="txt-preco" name="preco" value="${preco}" class="form-control moeda" size="20" 
					maxlength="10" required>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-outline-secondary mr-2" 
		onclick="irPara('/ecommerce-web/mvc?logica=ListarProduto');" tabindex="-1">
		<i class="fa fa-reply"></i> Voltar
	</button>
	<button type="submit" class="btn btn-success">
		<i class="fa fa-save"></i> Salvar
	</button>
</form>

<script>
	$(() => iniciarMascaras());
</script>

<c:import url="/rodape.jsp"/>
