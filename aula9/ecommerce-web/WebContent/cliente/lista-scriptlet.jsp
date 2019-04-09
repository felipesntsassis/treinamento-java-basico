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
	</head>
	<body>
		<h3>Cadastro de Clientes</h3>
		<p>
			<a href="editar-scriptlet.jsp">Novo Cliente</a>
		</p>
		<table border="1" width="750">
			<thead>
				<tr>
					<th width="200">Nome</th>
					<th width="200">E-mail</th>
					<th>Endereço</th>
					<th width="100">Data de Nascimento</th>
					<th width="80">Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
					ClienteDao dao = new ClienteDao();
					List<Cliente> clientes =  dao.listar();
					
					for (Cliente cliente : clientes) {
				%>
					<tr>
						<td width="200"><%=cliente.getNome() %></td>
						<td width="200"><%=cliente.getEmail() %></td>
						<td><%=cliente.getEndereco() %></td>
						<td width="100">
							<%=DateUtil.parseToString(cliente.getDataNascimento(), "dd/MM/YYYY") %>
						</td>
						<td width="80">
							<button type="button" 
								onclick="irPara('/ecommerce-web/cliente/editar-scriptlet.jsp?id=<%=cliente.getId()%>')">
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
				irPara('/ecommerce-web/cliente?id=' + id);
			}
		};
	</script>
</html>
