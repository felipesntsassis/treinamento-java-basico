<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="javax.swing.text.NumberFormatter"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Produto"%>
<%@page import="java.util.List"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Produtos</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Produtos</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Novo Produto</a></li>
				<li><a href="/ecommerce-web/">Voltar para Home</a></li>
			</ul>
		</nav>
		<table border="1">
			<thead>
				<tr>
					<th>Descrição</th>
					<th>Categoria</th>
					<th>Quantidade</th>
					<th>Preço Unitário</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
					ProdutoDao dao = new ProdutoDao();
					List<Produto> produtos =  dao.listar();
					
					for (Produto produto : produtos) {
				%>
					<tr>
						<td><%=produto.getDescricao()%></td>
						<td><%=produto.getCategoria().getDescricao()%></td>
						<td><%=produto.getQuantidade()%></td>
						<%
							NumberFormat nf = NumberFormat.getInstance();
							nf.setMinimumFractionDigits(2);
							nf.setMaximumFractionDigits(2);
							String preco = nf.format(produto.getPreco());
						%>
						<td><%=preco%></td>
						<td>
							<button type="button" 
								onclick="irPara('/ecommerce-web/admin/produto/editar-scriptlet.jsp?id=<%=produto.getId()%>')">
								Editar
							</button>
							<br>
							<button type="button" onclick="confirmaExclusao(<%=produto.getId()%>)">
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
			if (confirm('Deseja excluir este Produto?')) {
				irPara('/ecommerce-web/admin/produto?id=' + id);
			}
		};
	</script>
</html>
