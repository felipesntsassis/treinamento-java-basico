<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/cabecalho.jsp"/>

<div class="card">
	<div class="card-body text-center">
		<h1><i class="fa fa-2x fa-check-circle text-success"></i></h1>
		<h2 class="text-success">Produto ${acao} com sucesso!</h2>
		<hr>
		<button type="button" class="btn btn-outline-secondary mr-2"
			onclick="irPara('/ecommerce-web/admin/produto/lista-taglib.jsp');" tabindex="-1">
			<i class="fa fa-reply"></i> Voltar
		</button>
		<button type="button" class="btn btn-primary"
			onclick="irPara('/ecommerce-web/admin/produto/editar-taglib.jsp');">
			<i class="fa fa-plus"></i> Novo Produto
		</button>
	</div>
</div>

<c:import url="/rodape.jsp"/>
