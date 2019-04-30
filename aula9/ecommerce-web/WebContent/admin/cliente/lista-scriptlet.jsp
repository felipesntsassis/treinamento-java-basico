<%@ page import="java.util.List"%>
<%@ page import="br.com.escolpi.ecommerce.util.DateUtil"%>
<%@ page import="br.com.escolpi.ecommerce.modelo.Cliente"%>
<%@ page import="br.com.escolpi.ecommerce.jdbc.dao.ClienteDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Clientes</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Clientes</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Novo Cliente</a></li>
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
				<%
					ClienteDao dao = new ClienteDao();
					List<Cliente> clientes =  dao.listar();
					
					for (Cliente cliente : clientes) {
				%>
					<tr>
						<td><%=cliente.getNome() %></td>
						<td><%=cliente.getEmail() %></td>
						<td><%=cliente.getEndereco() %></td>
						<td>
							<%=DateUtil.parseToString(cliente.getDataNascimento(), "dd/MM/YYYY") %>
						</td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/cliente/editar-scriptlet.jsp?id=<%=cliente.getId()%>')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(<%=cliente.getId()%>)">
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
			if (confirm('Deseja excluir este cliente?')) {
				irPara('/ecommerce-web/admin/cliente?id=' + id);
			}
		};
	</script>
</html>
