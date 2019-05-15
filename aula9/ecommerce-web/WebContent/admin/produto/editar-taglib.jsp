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
	<small>* Campos obrigatórios</small>
	<p>
		<label for="txt-descricao">* Descrição:</label><br>
		<input type="text" id="txt-descricao" name="descricao" 
			value="${produto.descricao}" 
			size="50" maxlength="100" required autofocus>
	</p>
	<p>
		<label for="combo-categoria">* Categoria:</label><br>
		<select id="combo-categoria" name="categoria" required>
			<option ${!edicao ? 'selected' : ''}>Selecione</option>
			<c:forEach items="${categorias}" var="categoria">
				<option value="${categoria.id}" 
					${edicao and produto.categoria.id == categoria.id ? 'selected': ''}>
					${categoria.descricao}
				</option>
			</c:forEach>
		</select>
	</p>
	<p>
		<label for="txt-quantidade">* Quantidade:</label><br>
		<input type="number" id="txt-quantidade" name="quantidade" 
			value="${produto.quantidade}" 
			size="10" maxlength="3" min="0" max="100" step="1" required>
	</p>
	<p>
		<label for="txt-preco">* Preço Unitário:</label><br>
		R$
		<fmt:formatNumber var="preco" value="${produto.preco}" type="number"
			minFractionDigits="2" maxFractionDigits="2"/>
		<input type="text" id="txt-preco" name="preco" value="${preco}" 
			size="20" maxlength="10" required>
	</p>
	<p>
		<button type="submit">Salvar</button>
		<button type="button" onclick="irPara('lista-taglib.jsp');">Voltar</button>
	</p>
</form>

<c:import url="/rodape.jsp"/>
