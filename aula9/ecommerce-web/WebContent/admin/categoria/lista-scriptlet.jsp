<%@page import="br.com.escolpi.ecommerce.modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Categorias</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Categorias</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Nova Categoria</a></li>
				<li><a href="/ecommerce-web/">Voltar para Home</a></li>
			</ul>
		</nav>
		<table border="1">
			<thead>
				<tr>
					<th>Descrição</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
					CategoriaDao dao = new CategoriaDao();
					List<Categoria> categorias =  dao.listar();
					
					for (Categoria categoria : categorias) {
				%>
					<tr>
						<td><%=categoria.getDescricao()%></td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/categoria/editar-scriptlet.jsp?id=<%=categoria.getId()%>')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(<%=categoria.getId()%>)">
								Excluir
							</button>
						</td>
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
	<script>
		const confirmaExclusao = (id) => {
			if (confirm('Deseja excluir esta Categoria?')) {
				irPara('/ecommerce-web/admin/categoria?id=' + id);
			}
		};
	</script>
</html>
