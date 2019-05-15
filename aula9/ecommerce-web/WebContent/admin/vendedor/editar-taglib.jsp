<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty param.id }">
	<jsp:useBean id="vendedor" class="br.com.escolpi.ecommerce.modelo.Vendedor"/>
</c:if>
<c:if test="${not empty param.id and param.id > 0}">
	<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"/>
	<c:set var="vendedor" value="${dao.obter(param.id)}"/>
</c:if>

<c:set var="edicao" value="${not empty vendedor.id and vendedor.id > 0}"/>

<c:import url="/cabecalho.jsp"/>

<h3>${edicao ? "Editar" : "Cadastrar"} Vendedor</h3>
<form action="/ecommerce-web/admin/vendedor" method="POST">
	<c:if test="${edicao}">
		<input type="hidden" name="id" value="${vendedor.id}">
	</c:if>
	<small>* Campos obrigatórios</small>
	<p>
		<label>* Nome:</label><br>
		<input type="text" name="nome" value="${vendedor.nome}" maxlength="100" 
		required autofocus>
	</p>
	<p>
		<label for="txt-email">* E-mail:</label><br>
		<input type="email"name="email" value="${vendedor.email}" id="txt-email" 
			maxlength="100" required>
	</p>
	<p>
		<label>* Departamento:</label><br>
		<input type="text" name="departamento" value="${vendedor.departamento}" 
			maxlength="100" required>
	</p>
	<p>
		<label>* Percentual de Comissão:</label><br>
		<fmt:formatNumber var="percentualComissao" value="${vendedor.percentualComissao}"
			type="number" minFractionDigits="2" maxFractionDigits="2"/>
		<input type="text" name="percComissao" value="${percentualComissao}" maxlength="5" 
			required> %
	</p>
	<p>
		<button type="submit">Salvar</button>
		<button type="button" onclick="irPara('lista-taglib.jsp');">Voltar</button>
	</p>
</form>

<c:import url="/rodape.jsp"/>
