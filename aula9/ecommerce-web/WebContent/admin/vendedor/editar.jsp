<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="escolpi" %>

<c:set var="edicao" value="${not empty vendedor.id and vendedor.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Vendedor</h3>
<form action="/ecommerce-web/mvc?logica=SalvarVendedor" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${vendedor.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<div class="row mb-3">
		<div class="form-group col-12 col-lg-6">
			<label for="txt-nome" class="required">Nome:</label>
			<input type="text" name="nome" id="txt-nome" value="${vendedor.nome}" class="form-control" maxlength="100" 
				required autofocus>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="txt-email" class="required">E-mail:</label>
			<input type="email" name="email" id="txt-email" class="form-control" value="${vendedor.email}" id="txt-email" 
				maxlength="100" required>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="txt-departamento" class="required">Departamento:</label>
			<escolpi:comboBox items="${departamentos}" entity="${vendedor.departamento}" 
				name="departamento" id="combo-departamento" required="true"/>
<!-- 			<input type="text" name="departamento" id="txt-departamento" class="form-control"  -->
<%-- 				value="${vendedor.departamento}" maxlength="100" required> --%>
		</div>
		<div class="form-group col-12 col-lg-3">
			<label for="txt-perc-comissao" class="required">Percentual de Comissão:</label>
			<div class="input-group">
				<fmt:formatNumber var="percentualComissao" value="${vendedor.percentualComissao}"
					type="number" minFractionDigits="2" maxFractionDigits="2"/>
				<input type="text" name="percComissao" id="txt-perc-comissao" class="form-control porcentagem"
					 value="${percentualComissao}" maxlength="5" required>
				<div class="input-group-append">
					<div class="input-group-text">%</div>
				</div>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-outline-secondary mr-2" 
		onclick="irPara('/ecommerce-web/mvc?logica=ListarVendedor');" tabindex="-1">
		<i class="fa fa-reply"></i> Voltar
	</button>
	<button type="submit" class="btn btn-success">
		<i class="fa fa-save"></i> Salvar
	</button>
</form>

 <script>
	$(() => iniciarMascaras())
</script>

<c:import url="/rodape.jsp"/>
