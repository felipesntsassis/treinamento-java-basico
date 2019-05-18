<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"/>
<c:set var="produtos" value="${dao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<div class="row">
	<div class="col-12">
		<h3>Cadastro de Produtos</h3>
		<nav>
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-primary" href="editar-taglib.jsp">
						<i class="fa fa-plus-square"></i> Novo Produto
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-12">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">Descrição</th>
					<th scope="col">Categoria</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Preço Unitário</th>
					<th scope="col" class="text-center">Opções</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty produtos }">
						<c:forEach items="${produtos}" var="produto" varStatus="index">
							<tr>
								<td>${produto.descricao}</td>
								<td>${produto.categoria.descricao}</td>
								<td>${produto.quantidade}</td>
								<td>
									<fmt:formatNumber var="preco" value="${produto.preco}" type="currency"/>
									${preco}
								</td>
								<td>
									<div class="dropdown">
										<button type="button" class="btn btn-sm btn-outline-primary" data-toggle="dropdown" 
											data-offset="-130,0" aria-haspopup="true" aria-expanded="false">
											<i class="fa fa-bars"></i>
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item text-primary" 
												href="/ecommerce-web/admin/produto/editar-taglib.jsp?id=${produto.id}">
												<i class="fa fa-edit"></i> Editar
											</a>
											<a class="dropdown-item text-danger" href="#" onclick="confirmaExclusao(${produto.id})">
												<i class="fa fa-trash"></i> Excluir
											</a>
										</div>
									</div>
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
	</div>
</div>
<script>
	const confirmaExclusao = (id) => {
		if (confirm('Deseja excluir este Produto?')) {
			irPara('/ecommerce-web/admin/produto/excluir?id=' + id);
		}
	};
</script>

<c:import url="/rodape.jsp"/>
