<%@page import="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Categorias</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<%
			Categoria categoria = new Categoria();
			String idRequest = request.getParameter("id");

			if (idRequest != null && idRequest != "") {
				CategoriaDao dao = new CategoriaDao();
				categoria = dao.obter(Long.valueOf(idRequest));
			}

			boolean edicao = (categoria.getId() != null && categoria.getId() > 0);
		%>
		<h3><%=edicao ? "Editar" : "Cadastrar" %> Categoria</h3>
		<form action="/ecommerce-web/admin/categoria" method="POST">
			<% if (edicao) { %>
				<input type="hidden" name="id" value="<%=categoria.getId() %>">
			<% } %>
			<small>* Campos obrigatórios</small>
			<p>
				<label for="txt-descricao">* Descrição:</label><br>
				<input type="text" id="txt-descricao" name="descricao" 
					value="<%=edicao ? categoria.getDescricao() : "" %>" 
					size="50" maxlength="100" required autofocus>
			</p>
			<p>
				<button type="submit">Salvar</button>
				<button type="button" onclick="irPara('lista-scriptlet.jsp');">Voltar</button>
			</p>
		</form>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
</html>
