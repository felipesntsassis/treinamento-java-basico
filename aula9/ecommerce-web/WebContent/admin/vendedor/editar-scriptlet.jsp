<%@page import="br.com.escolpi.ecommerce.util.NumberUtil"%>
<%@page import="br.com.escolpi.ecommerce.util.DateUtil"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Vendedor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Vendedores</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<%
			Vendedor vendedor = new Vendedor();
			String idRequest = request.getParameter("id");

			if (idRequest != null && idRequest != "") {
				VendedorDao dao = new VendedorDao();
				vendedor = dao.obter(Long.valueOf(idRequest));
			}

			boolean edicao = (vendedor.getId() != null && vendedor.getId() > 0);
		%>
		<h3><%=edicao ? "Editar" : "Cadastrar" %> Vendedor</h3>
		<form action="/ecommerce-web/admin/vendedor" method="POST">
			<% if (edicao) { %>
				<input type="hidden" name="id" value="<%=vendedor.getId() %>">
			<% } %>
			<small>* Campos obrigatórios</small>
			<p>
				<label>* Nome:</label><br>
				<input type="text" name="nome" value="<%=edicao ? vendedor.getNome() : "" %>" maxlength="100" required autofocus>
			</p>
			<p>
				<label for="txt-email">* E-mail:</label><br>
				<input type="email"name="email" value="<%=edicao ? vendedor.getEmail() : "" %>" id="txt-email" maxlength="100" required>
			</p>
			<p>
				<label>* Departamento:</label><br>
				<input type="text" name="departamento" value="<%=edicao ? vendedor.getDepartamento() : "" %>" maxlength="100" required>
			</p>
			<%
				String percentualComissao = "";

				if (edicao) {
					percentualComissao = NumberUtil.formatarPercentual(vendedor.getPercentualComissao(), false);
				}
			%>
			<p>
				<label>* Percentual de Comissão:</label><br>
				<input type="text" name="percComissao" value="<%=edicao ? percentualComissao : "" %>" maxlength="5" required> %
			</p>
			<p>
				<button type="submit">Salvar</button>
				<button type="button" onclick="irPara('lista-scriptlet.jsp');">Voltar</button>
			</p>
		</form>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
</html>
