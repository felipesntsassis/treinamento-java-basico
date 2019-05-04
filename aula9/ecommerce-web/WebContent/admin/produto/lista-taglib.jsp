<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"/>
<c:set var="produtos" value="${dao.listar()}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Produtos</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Produtos</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Novo Produto</a></li>
				<li><a href="/ecommerce-web/">Voltar para Home</a></li>
			</ul>
		</nav>
		<table border="1">
			<thead>
				<tr>
					<th>Descrição</th>
					<th>Categoria</th>
					<th>Quantidade</th>
					<th>Preço Unitário</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty produtos }">
						<c:forEach items="${produtos}" var="produto">
							<tr>
								<td>${produto.descricao}</td>
								<td>${produto.categoria.descricao}</td>
								<td>${produto.quantidade}</td>
								<td>
									<fmt:formatNumber var="preco" value="${produto.preco}" type="currency"/>
									${preco}
								</td>
								<td>
									<button type="button" 
										onclick="irPara('/ecommerce-web/admin/produto/editar?id=${produto.id}')">
										Editar
									</button>
									<br>
									<button type="button" onclick="confirmaExclusao(${produto.id})">
										Excluir
									</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">
								<em>Nenhum Produto encontrado.</em>
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
			if (confirm('Deseja excluir este Produto?')) {
				irPara('/ecommerce-web/admin/produto/excluir?id=' + id);
			}
		};
	</script>
</html>
