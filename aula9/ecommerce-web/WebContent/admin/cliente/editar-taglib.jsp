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
	<small>* Campos obrigatórios</small>
	<p>
		<label>* Nome:</label><br>
		<input type="text" name="nome" value="${cliente.nome}" maxlength="100" required autofocus>
	</p>
	<p>
		<label for="txt-email">* E-mail:</label><br>
		<input type="email"name="email" value="${cliente.email}" id="txt-email" maxlength="100" required>
	</p>
	<p>
		<label>* Endereço:</label><br>
		<input type="text" name="endereco" value="${cliente.endereco}" maxlength="100" required>
	</p>
	<p>
		<label>* Data de Nascimento:</label><br>
		<fmt:formatDate var="dataNascimento" value="${cliente.dataNascimento.time}" 
			pattern="dd/MM/yyyy"/>
		<input type="text" name="dataNascimento" value="${dataNascimento}" maxlength="100" required>
	</p>
	<p>
		<button type="submit">Salvar</button>
		<button type="button" onclick="irPara('lista-taglib.jsp');">Voltar</button>
	</p>
</form>

<c:import url="/rodape.jsp"/>
