<%@page import="br.com.escolpi.ecommerce.modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Produto"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Cadastro de Produtos</title>
	</head>
	<body>
		<%
			Produto produto = new Produto();
			String idRequest = request.getParameter("id");

			if (idRequest != null && idRequest != "") {
				ProdutoDao dao = new ProdutoDao();
				produto = dao.obter(Long.valueOf(idRequest));
			}

			boolean edicao = (produto.getId() != null && produto.getId() > 0);
		%>
		<h3><%=edicao ? "Editar" : "Cadastrar" %> Produto</h3>
		<form action="/ecommerce-web/produto" method="POST">
			<% if (edicao) { %>
				<input type="hidden" name="id" value="<%=produto.getId() %>">
			<% } %>
			<small>* Campos obrigatórios</small>
			<p>
				<label for="txt-descricao">* Descrição:</label><br>
				<input type="text" id="txt-descricao" name="descricao" 
					value="<%=edicao ? produto.getDescricao() : "" %>" 
					size="50" maxlength="100" required autofocus>
			</p>
			<p>
				<label for="combo-categoria">* Categoria:</label><br>
				<select id="combo-categoria" name="categoria" required>
					<option <%=!edicao ? "selected" : "" %>>Selecione</option>
					<%
						CategoriaDao categoriaDao = new CategoriaDao();
						List<Categoria> opcoesCategoria = categoriaDao.listar();
						
						for(Categoria categoria : opcoesCategoria) {
					%>
						<option
							value="<%=categoria.getId()%>" 
							<%=
								edicao && produto.getCategoria().getId() == categoria.getId() 
									? "selected": ""
							%>
						>
							<%=categoria.getDescricao()%>
						</option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<label for="txt-quantidade">* Quantidade:</label><br>
				<input type="number" id="txt-quantidade" name="quantidade" 
					value="<%=edicao ? produto.getQuantidade() : "" %>" 
					size="10" maxlength="3" min="0" max="100" step="1" required>
			</p>
			<p>
				<label for="txt-preco">* Preço Unitário:</label><br>
				R$
				<input type="text" id="txt-preco" name="preco" 
					value="<%=edicao ? produto.getPreco() : "" %>" 
					size="20" maxlength="10" required>
			</p>
			<p>
				<button type="submit">Salvar</button>
				<button type="button" onclick="irPara('lista-scriptlet.jsp');">Voltar</button>
			</p>
		</form>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
</html>
