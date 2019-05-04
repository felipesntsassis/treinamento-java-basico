<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"/>
<c:set var="categorias" value="${dao.listar()}"/>

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
				<c:choose>
					<c:when test="${!empty categorias}">
						<c:forEach items="${categorias}" var="categoria">
							<tr>
								<td>${categoria.descricao}</td>
								<td>
									<button type="button" 
										onclick="irPara('/ecommerce-web/admin/categoria/editar?id=${categoria.id}')">
										Editar
									</button>
									<br>
									<button type="button" onclick="confirmaExclusao(${categoria.id})">
										Excluir
									</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="2">
								<em>Nenhuma Categoria foi encontrada.</em>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
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
