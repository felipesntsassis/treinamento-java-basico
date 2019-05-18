<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty param.id}">
	<jsp:useBean id="produto" class="br.com.escolpi.ecommerce.modelo.Produto"/>
</c:if>
<c:if test="${not empty param.id and param.id > 0 }">
	<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"/>
	<c:set var="produto" value="${dao.obter(param.id)}"/>
</c:if>

<c:set var="edicao" value="${not empty param.id and param.id > 0 }"/>

<jsp:useBean id="categoriaDao" class="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"/>
<c:set var="categorias" value="${categoriaDao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? 'Editar' : 'Cadastrar'} Produto</h3>
<form action="/ecommerce-web/admin/produto" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${produto.id}">
	</c:if>
	<div class="row">
		<div class="form-group col-12">
			<small class="required">Campos obrigatórios</small>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-12 col-lg-6">
			<label for="txt-descricao" class="required">Descrição:</label>
			<input type="text" id="txt-descricao" name="descricao" class="form-control" value="${produto.descricao}" 
				size="50" maxlength="100" required autofocus>
		</div>
		<div class="form-group col-12 col-lg-6">
			<label for="combo-categoria" class="required">Categoria:</label>
			<select id="combo-categoria" name="categoria" class="form-control" required>
				<option ${!edicao ? 'selected' : ''}>Selecione</option>
				<c:forEach items="${categorias}" var="categoria">
					<option value="${categoria.id}" 
						${edicao and produto.categoria.id == categoria.id ? 'selected': ''}>
						${categoria.descricao}
					</option>
				</c:forEach>
			</select>
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
				<input type="text" id="txt-preco" name="preco" value="${preco}" class="form-control" size="20" 
					maxlength="10" required>
			</div>
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
