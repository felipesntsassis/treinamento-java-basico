<%@ page import="br.com.escolpi.ecommerce.util.DateUtil" %>
<%@ page import="br.com.escolpi.ecommerce.jdbc.dao.ClienteDao" %>
<%@ page import="br.com.escolpi.ecommerce.modelo.Cliente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Clientes</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<%
			Cliente cliente = new Cliente();
			String idRequest = request.getParameter("id");

			if (idRequest != null && idRequest != "") {
				ClienteDao dao = new ClienteDao();
				cliente = dao.obter(Long.valueOf(idRequest));
			}

			boolean edicao = (cliente.getId() != null && cliente.getId() > 0);
		 %>
		<h3><%=edicao ? "Editar" : "Cadastrar" %> Cliente</h3>
		<form action="/ecommerce-web/admin/cliente" method="POST">
			<% if (edicao) { %>
				<input type="hidden" name="id" value="<%=cliente.getId() %>">
			<% } %>
			<small>* Campos obrigatórios</small>
			<p>
				<label>* Nome:</label><br>
				<input type="text" name="nome" value="<%=edicao ? cliente.getNome() : "" %>" maxlength="100" required autofocus>
			</p>
			<p>
				<label for="txt-email">* E-mail:</label><br>
				<input type="email"name="email" value="<%=edicao ? cliente.getEmail() : "" %>" id="txt-email" maxlength="100" required>
			</p>
			<p>
				<label>* Endereço:</label><br>
				<input type="text" name="endereco" value="<%=edicao ? cliente.getEndereco() : "" %>" maxlength="100" required>
			</p>
			<%
				String dataNascimento = "";

				if (edicao) {
					dataNascimento = DateUtil.parseToString(cliente.getDataNascimento(), "dd/MM/yyyy");
				}
			 %>
			<p>
				<label>* Data de Nascimento:</label><br>
				<input type="text" name="dataNascimento" value="<%=dataNascimento %>" maxlength="100" required>
			</p>
			<p>
				<button type="submit">Salvar</button>
				<button type="button" onclick="irPara('lista-scriptlet.jsp');">Voltar</button>
			</p>
		</form>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
</html>
