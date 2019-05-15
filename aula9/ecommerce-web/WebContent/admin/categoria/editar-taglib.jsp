<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ empty param.id }">
	<jsp:useBean id="categoria" class="br.com.escolpi.ecommerce.modelo.Categoria"/>
</c:if>
<c:if test="${ not empty param && param.id > 0 }">
	<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"/>
	<c:set var="categoria" value="${dao.obter(param.id)}"/>
</c:if>

<c:set var="edicao" value="${not empty categoria.id and categoria.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Categoria</h3>
<form action="/ecommerce-web/admin/categoria" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${categoria.id}">
	</c:if>
	<div class="row">
		<div class="col-12">
			<span class="required">Campos obrigatórios</span>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-lg-6 form-group">
			<label for="txt-descricao" class="required">Descrição:</label>
			<input type="text" id="txt-descricao" name="descricao" value="${categoria.descricao}" 
				class="form-control" size="50" maxlength="100" required autofocus>
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
