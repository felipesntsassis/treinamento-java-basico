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
	</head>
	<body>
		<h3>Cadastro de Produtos</h3>
		<p>
			<a href="editar-scriptlet.jsp">Novo Produto</a>
		</p>
		<table border="1" width="750">
			<thead>
				<tr>
					<th>Descrição</th>
					<th width="250">Categoria</th>
					<th width="100">Quantidade</th>
					<th width="80">Preço Unitário</th>
					<th width="80">Opções</th>
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
						<td width="250"><%=produto.getCategoria().getDescricao()%></td>
						<td width="80"><%=produto.getQuantidade()%></td>
						<%
							NumberFormat nf = NumberFormat.getInstance();
							nf.setMinimumFractionDigits(2);
							nf.setMaximumFractionDigits(2);
							String preco = nf.format(produto.getPreco());
						%>
						<td width="80"><%=preco%></td>
						<td width="80">
							<button type="button" 
								onclick="irPara('/ecommerce-web/produto/editar-scriptlet.jsp?id=<%=produto.getId()%>')">
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
				irPara('/ecommerce-web/produto?id=' + id);
			}
		};
	</script>
</html>
