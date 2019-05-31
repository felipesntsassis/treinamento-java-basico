<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"/>
<c:set var="vendedores" value="${dao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<div class="row">
	<div class="col-12">
		<h3>Cadastro de Vendedores</h3>
		<nav class="mb-2">
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-primary" href="editar-taglib.jsp">
						<i class="fa fa-plus-square"></i> Novo Vendedor
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>


<div class="row">
	<div class="col-12">
		<div class="table-responsive-sm">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">Nome</th>
						<th scope="col">E-mail</th>
						<th scope="col">Departamento</th>
						<th scope="col">Perc. de Comissão</th>
						<th scope="col" class="text-center">Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${!empty vendedores}">
							<c:forEach items="${vendedores}" var="vendedor" varStatus="index">
								<tr>
									<td>${vendedor.nome}</td>
									<td>${vendedor.email}</td>
									<td>${vendedor.departamento}</td>
									<td>
										<fmt:formatNumber var="percentualComissao" value="${vendedor.percentualComissao}"
											type="number" minFractionDigits="2" maxFractionDigits="2"/>
										${percentualComissao} %
									</td>
									<td class="text-center">
										<div class="dropdown">
											<button type="button" class="btn btn-sm btn-outline-primary" data-toggle="dropdown" 
												data-offset="-130,0" aria-haspopup="true" aria-expanded="false">
												<i class="fa fa-bars"></i>
											</button>
											<div class="dropdown-menu">
												<a class="dropdown-item text-primary" 
													href="/ecommerce-web/admin/vendedor/editar-taglib.jsp?id=${vendedor.id}">
													<i class="fa fa-edit"></i> Editar
												</a>
												<a class="dropdown-item text-danger" href="#" onclick="confirmaExclusao(${vendedor.id})">
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
									<em>Nenhum Vendedor cadastrado.</em>
								</td>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	const confirmaExclusao = (id) => {
		if (confirm('Deseja excluir este vendedor?')) {
			irPara('/ecommerce-web/admin/vendedor?id=' + id);
		}
	};
</script>

<c:import url="/rodape.jsp"/>
