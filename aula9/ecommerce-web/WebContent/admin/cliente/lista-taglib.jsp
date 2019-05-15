<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.ClienteDao"/>
<c:set var="clientes" value="${dao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<h3>Cadastro de Clientes</h3>
<nav>
	<ul>
		<li><a href="editar-taglib.jsp">Novo Cliente</a></li>
		<li><a href="/ecommerce-web/">Voltar para Home</a></li>
	</ul>
</nav>
<table border="1">
	<thead>
		<tr>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Endereço</th>
			<th>Data de Nascimento</th>
			<th>Opções</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${!empty clientes}">
				<c:forEach items="${clientes}" var="cliente" varStatus="index">
					<tr class="${ index.count % 2 == 0 ? 'linha-zebrada' : 'linha' }">
						<td>${cliente.nome}</td>
						<td>${cliente.email}</td>
						<td>${cliente.endereco}</td>
						<td>
							<fmt:formatDate var="dataNascimento" value="${ cliente.dataNascimento.time }" 
								pattern="dd/MM/yyyy"/>
							${dataNascimento}
						</td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/cliente/editar-taglib.jsp?id=${cliente.id}')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(${cliente.id})">
								Excluir
							</button>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">
						<em>Nenhum Cliente foi encontrado.</em>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<script>
	const confirmaExclusao = (id) => {
		if (confirm('Deseja excluir este cliente?')) {
			irPara('/ecommerce-web/admin/cliente/excluir?id=' + id);
		}
	};
</script>

<c:import url="/rodape.jsp"/>