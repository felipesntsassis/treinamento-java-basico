<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"/>
<c:set var="vendedores" value="${dao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<h3>Cadastro de Vendedores</h3>
<nav>
	<ul>
		<li><a href="editar-taglib.jsp">Novo Vendedor</a></li>
		<li><a href="/ecommerce-web/">Voltar para Home</a></li>
	</ul>
</nav>
<table border="1">
	<thead>
		<tr>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Departamento</th>
			<th>Perc. de Comissão</th>
			<th>Opções</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${!empty vendedores}">
				<c:forEach items="${vendedores}" var="vendedor" varStatus="index">
					<tr class="${ index.count % 2 == 0 ? 'linha-zebrada' : 'linha' }">
						<td>${vendedor.nome}</td>
						<td>${vendedor.email}</td>
						<td>${vendedor.departamento}</td>
						<td>
							<fmt:formatNumber var="percentualComissao" value="${vendedor.percentualComissao}"
								type="number" minFractionDigits="2" maxFractionDigits="2"/>
							${percentualComissao} %
						</td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/vendedor/editar-taglib.jsp?id=${vendedor.id}')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(${vendedor.id})">
								Excluir
							</button>
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
<script>
	const confirmaExclusao = (id) => {
		if (confirm('Deseja excluir este vendedor?')) {
			irPara('/ecommerce-web/admin/vendedor/excluir?id=' + id);
		}
	};
</script>

<c:import url="/rodape.jsp"/>
