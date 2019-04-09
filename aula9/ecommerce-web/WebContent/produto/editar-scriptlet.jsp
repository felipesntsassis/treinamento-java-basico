<%@page import="br.com.escolpi.ecommerce.modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Produto</title>
	</head>
	<body>
		<form action="/ecommerce-web/produto" method="POST">
			<p>
				<label>* Categoria:</label><br>
				<select name="categoriaId" id="combo-categoria">
					<option value="">Selecione a Categoria</option>
					<%
						CategoriaDao categoriaDao = new CategoriaDao();
						List<Categoria> categorias = categoriaDao.listar();
						
						for (Categoria categoria : categorias) {
					%>
						<option value="<%=categoria.getId() %>">
							<%=categoria.getDescricao() %>
						</option>
					<%
						}
					%>
				</select>
			</p>
		</form>
	</body>
</html>