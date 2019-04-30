<%@page import="br.com.escolpi.ecommerce.util.NumberUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="br.com.escolpi.ecommerce.util.DateUtil"%>
<%@ page import="br.com.escolpi.ecommerce.modelo.Vendedor"%>
<%@ page import="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Vendedores</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Vendedores</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Novo Vendedor</a></li>
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
				<%
					VendedorDao dao = new VendedorDao();
					List<Vendedor> vendedores =  dao.listar();
					
					for (Vendedor vendedor : vendedores) {
				%>
					<tr>
						<td><%=vendedor.getNome() %></td>
						<td><%=vendedor.getEmail() %></td>
						<td><%=vendedor.getDepartamento() %></td>
						<td><%=NumberUtil.formatarPercentual(vendedor.getPercentualComissao(), true)%></td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/vendedor/editar-scriptlet.jsp?id=<%=vendedor.getId()%>')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(<%=vendedor.getId()%>)">
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
			if (confirm('Deseja excluir este vendedor?')) {
				irPara('/ecommerce-web/admin/vendedor?id=' + id);
			}
		};
	</script>
</html>
