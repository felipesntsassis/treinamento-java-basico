<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>E-Commerce: Bem Vindo</title>
		<link href="/ecommerce-web/assets/css/fontawesome/css/all.css" rel="stylesheet">
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
		<link href="/ecommerce-web/assets/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="/ecommerce-web/assets/js/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
		<script src="/ecommerce-web/assets/js/jquery-3.4.0.min.js"></script>
		<script src="/ecommerce-web/assets/js/popper.min.js"></script>
		<script src="/ecommerce-web/assets/js/bootstrap/js/bootstrap.min.js"></script>
		<script src="/ecommerce-web/assets/js/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
		<script src="/ecommerce-web/assets/js/bootstrap-datepicker/locales/bootstrap-datepicker.pt-BR.min.js"></script>
		<script src="/ecommerce-web/assets/js/jquery-mask/jquery.mask.min.js"></script>
	</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
		<a class="navbar-brand" href="/ecommerce-web">E-commerce Escolpi</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu-principal"
			aria-controls="menu-principal" aria-expanded="false" aria-label="Alternar navegação">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="menu-principal">
			<ul class="navbar-nav">
				<li class="nav-item ${empty modulo ? 'active' : ''}">
					<a class="nav-link" href="/ecommerce-web/">Início <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item dropdown ${not empty modulo ? 'active' : ''}">
					<a class="nav-link dropdown-toggle" href="#" id="menu-cadastros"
						role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Cadastros
					</a>
					<div class="dropdown-menu" aria-labelledby="menu-cadastros">
						<a class="dropdown-item ${modulo eq 'categoria' ? 'active' : '' }" 
							href="/ecommerce-web/mvc?logica=ListarCategoria">
							Categorias
						</a>
						<a class="dropdown-item ${modulo eq 'cliente' ? 'active' : '' }" 
							href="/ecommerce-web/mvc?logica=ListarCliente">
							Clientes
						</a>
						<a class="dropdown-item ${modulo eq 'departamento' ? 'active' : '' }" 
							href="/ecommerce-web/mvc?logica=ListarDepartamento">
							Departamentos
						</a>
						<a class="dropdown-item ${modulo eq 'produto' ? 'active' : '' }" 
							href="/ecommerce-web/mvc?logica=ListarProduto">
							Produtos
						</a>
						<a class="dropdown-item ${modulo eq 'vendedor' ? 'active' : '' }" 
							href="/ecommerce-web/mvc?logica=ListarVendedor">
							Vendedores
						</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="menu-vendas"
						role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Vendas
					</a>
					<div class="dropdown-menu" aria-labelledby="menu-vendas">
						<a class="dropdown-item" href="/ecommerce-web/admin/pedido/lista-scriptlet.jsp">
							Pedidos
						</a>
					</div>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
		<c:if test="${not empty feedback}">
			<div class="row">
				<div class="col-12 justify-content-center">
					<div class="alert alert-${feedback.tipoFeedback.estilo} alert-dismissible fade show" role="alert">
						${feedback.mensagem}
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</div>
			</div>
		</c:if>
