<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Bem Vindo</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
<body>
	<%-- comentário JSP aqui: nossa primeira página JSP --%>
	<h1>Bem vindo ao E-Commerce Escolpi!</h1>
	<div class="coluna-menu">
		<h3>Cadastros</h3>
		<nav class="vertical">
			<ul>
				<li><a href="/ecommerce-web/admin/categoria/lista-taglib.jsp">Categorias</a></li>
				<li><a href="/ecommerce-web/admin/cliente/lista-taglib.jsp">Clientes</a></li>
				<li><a href="/ecommerce-web/admin/produto/lista-taglib.jsp">Produtos</a></li>
				<li><a href="/ecommerce-web/admin/vendedor/lista-taglib.jsp">Vendedores</a></li>
			</ul>
		</nav>
	</div>
	<div class="coluna-menu">
		<h3>Vendas</h3>
		<nav class="vertical">
			<ul>
				<li><a href="/ecommerce-web/admin/pedido/lista-scriptlet.jsp">Pedidos</a></li>
			</ul>
		</nav>
	</div>
	<footer>
		<p>&copy; <%=Calendar.getInstance().get(Calendar.YEAR)%> Desenvolvido por Felipe Assis</p>
	</footer>
</body>
</html>