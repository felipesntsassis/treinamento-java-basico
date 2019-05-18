<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${ empty param.id }">
	<jsp:useBean id="cliente" class="br.com.escolpi.ecommerce.modelo.Cliente"/>
</c:if>
<c:if test="${ not empty param.id and param.id > 0 }">
	<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.ClienteDao"/>
	<c:set var="cliente" value="${dao.obter(param.id)}"/>
</c:if>

<c:set var="edicao" value="${not empty cliente.id && cliente.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Cliente</h3>
<form action="/ecommerce-web/admin/cliente" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${cliente.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">* Campos obrigatórios</small>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-12 col-lg-6">
			<label for="txt-nome" class="required">Nome:</label>
			<input type="text" name="nome" id="txt-nome" class="form-control" value="${cliente.nome}" maxlength="100" required autofocus>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="txt-email" class="required">E-mail:</label>
			<input type="email"name="email" class="form-control" value="${cliente.email}" id="txt-email" 
				maxlength="100" required>
		</div>
		<div class="form-group col-12">
			<label for="txt-endereco" class="required">Endereço:</label>
			<input type="text" name="endereco" class="form-control" value="${cliente.endereco}" maxlength="100" required>
		</div>
		<div class="form-group col-12 col-lg-4">
			<label for="txt-data-nascimento" class="required">Data de Nascimento:</label>
			<fmt:formatDate var="dataNascimento" value="${cliente.dataNascimento.time}" 
				pattern="dd/MM/yyyy"/>
			<input type="text" name="dataNascimento" id="txt-data-nascimento" class="form-control" 
				value="${dataNascimento}" maxlength="100" required>
		</div>
	</div>
	<ul class="nav">
		<li class="nav-item mr-2">
			<button type="submit" class="btn btn-success">
				<i class="fa fa-save"></i> Salvar
			</button>
		</li>
		<li class="nav-item">
			<button type="button" class="btn btn-outline-secondary" 
				onclick="irPara('lista-taglib.jsp');">
				<i class="fa fa-reply"></i> Voltar
			</button>
		</li>
	</ul>
</form>

<c:import url="/rodape.jsp"/>
