<%@page import="java.util.Optional"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.ItemPedidoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.escolpi.ecommerce.modelo.ItemPedido"%>
<%@page import="br.com.escolpi.ecommerce.util.NumberUtil"%>
<%@page import="javax.print.attribute.standard.NumberUp"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Produto"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao"%>
<%@page import="br.com.escolpi.ecommerce.enumerador.SituacaoPedido"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Cliente"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.ClienteDao"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.VendedorDao"%>
<%@page import="java.util.List"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Vendedor"%>
<%@page import="br.com.escolpi.ecommerce.jdbc.dao.PedidoDao"%>
<%@page import="br.com.escolpi.ecommerce.modelo.Pedido"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Lançar Pedido</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<%
			Pedido pedido = new Pedido();
			PedidoDao dao = new PedidoDao();
			String idRequest = request.getParameter("id");
			if (idRequest != null && idRequest != "") {
				pedido = dao.obter(Long.valueOf(idRequest));
			}

			boolean edicao = (pedido.getId() != null && pedido.getId() > 0);
		%>
		<h3><%=edicao ? "Editar" : "Cadastrar" %> Pedido</h3>
		<form id="form-pedido" action="/ecommerce-web/admin/pedido" method="POST">
			<input type="hidden" name="id" value="<%=pedido.getId() != null && pedido.getId() > 0 ? pedido.getId() : "" %>">
			<small>* Campos obrigatórios</small>
			<p>
				<label for="combo-vendedor">* Vendedor:</label><br>
				<%
					VendedorDao vendedorDao = new VendedorDao();
					List<Vendedor> vendedores = vendedorDao.listar();
				%>
				<select id="combo-vendedor" name="vendedorId" required>
					<option value=""<%=!edicao ? " selected" : "" %>>Selecione</option>
					<% for(Vendedor vendedor : vendedores) { %>
						<option value="<%=vendedor.getId()%>"<%=(edicao && pedido.getVendedor().getId() == vendedor.getId()) ? " selected" : "" %>>
							<%=vendedor.getNome()%>
						</option>
					<% } %>
				</select>
			</p>
			<p>
				<label for="combo-cliente">* Cliente:</label><br>
				<%
					ClienteDao clienteDao = new ClienteDao();
					List<Cliente> clientes = clienteDao.listar();
				%>
				<select id="combo-cliente" name="clienteId" required>
					<option value=""<%=!edicao ? " selected" : "" %>>Selecione</option>
					<% for(Cliente cliente : clientes) { %>
						<option value="<%=cliente.getId()%>"<%=(edicao && pedido.getCliente().getId() == cliente.getId()) ? " selected" : "" %>>
							<%=cliente.getNome()%>
						</option>
					<% } %>
				</select>
			</p>
			<% if (edicao) { %>
				<p>
					<label for="combo-pedido">* Situação do Pedido:</label><br>
					<select id="combo-pedido" name="situacao" required>
						<option value=""<%=!edicao ? " selected" : "" %>>Selecione</option>
						<%
							for (SituacaoPedido situacao : SituacaoPedido.listarNaoCancelados()) {
						%>
							<option value="<%=situacao.ordinal()%>" <%=edicao && pedido.getSituacao().equals(situacao) ? " selected" : ""%>>
								<%=situacao.getDescricao()%>
							</option>
						<%
							}
						%>
					</select>
				</p>
			<% } %>
			<fieldset id="field-item-pedido">
				<legend>Itens do Pedido</legend>
				<table class="table-form">
					<tbody>
						<tr id="subform-item-pedido" >
							<td>
								<input type="hidden" id="hd-item-pedido-id" name="itemPedidoId" value="">
								<label for="combo-produto">Produto:</label><br>
								<%
									ProdutoDao produtoDao = new ProdutoDao();
									List<Produto> produtos = produtoDao.listar();
									
									if (produtos != null && produtos.size() > 0) {
								%>
										<select id="combo-produto" name="produto">
											<option value="" data-preco="0" data-descricao="">Selecione</option>
								<%
											for (Produto produto : produtos) {
								%>
												<option value="<%=produto.getId()%>" 
													data-preco="<%=NumberUtil.formatarMoeda(produto.getPreco(), false)%>"
													data-descricao="<%=produto.getDescricao()%>">
													<%=produto.getDescricao()%> - 
													<%=NumberUtil.formatarMoeda(produto.getPreco(), true)%>
												</option>
								<%
											}
								%>
										</select>
								<%
									} else {
								%>
									<em>Produto não disponível, por favor verifique!</em>
								<%
									}
								%>
							</td>
							<td>
								<label for="txt-quantidade">Quantidade:</label><br>
								<input type="number" id="txt-quantidade" name="quantidade" min="0" step="1" max="100">
							</td>
							<td>
								<label for="txt-valor">Valor (R$):</label><br>
								<input type="text" id="txt-valor" name="valor" readonly>
							</td>
							<td id="acoes-item-pedido" class="text-right">
								<br>
								<button type="button" onclick="incluirItem()">Incluir Item</button>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table id="tb-itens-produtos" border="1" class="w-100">
									<thead>
										<tr>
											<th>Item</th>
											<th>Preço</th>
											<th>Quantidade</th>
											<th>Valor do Item</th>
											<th>Opções</th>
										</tr>
									</thead>
									<tbody>
										<%
											List<ItemPedido> itensPedido = new ArrayList<>();
											ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
											Double totalPedido = 0.0d;

											if (edicao) {
												itensPedido = itemPedidoDao.listarPorPedido(pedido.getId());
												for (ItemPedido item: itensPedido) {
										%>
													<tr>
														<td>
															<input type="hidden" name="itemPedido.id" value="<%=item.getId()%>">
															<input type="hidden" name="itemPedido.idPedido" value="<%=item.getPedido().getId()%>">
															<input type="hidden" name="itemPedido.produtoId" value="<%=item.getProduto().getId()%>">
															<input type="hidden" name="itemPedido.preco" value="<%=item.getProduto().getPreco()%>">
															<input type="hidden" name="itemPedido.quantidade" value="<%=item.getQuantidade()%>">
															<input type="hidden" name="itemPedido.valor" value="<%=item.getValor()%>">
															<%=item.getProduto().getDescricao()%>
														</td>
														<td>
															<%=NumberUtil.formatarMoeda(item.getProduto().getPreco(), true)%>
														</td>
														<td>
															<%=item.getQuantidade()%>
														</td>
														<td>
															<%=NumberUtil.formatarMoeda(item.getValor(), true)%>
														</td>
														<td class="text-center">
															<button type="button" onclick="editarItem(event);">Editar</button><br>
															<button type="button" onclick="removerItem(event);">Remover</button>
														</td>
													</tr>
										<%
													totalPedido += item.getValor();
												}
											} else {
										%>
											<tr data-sem-registros="true">
												<td colspan="5" class="text-center">
													<em>Nenhum item incluso neste Pedido</em>
												</td>
											</tr>
										<%
											}
										%>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="5" class="text-right">
												<strong>Subtotal:</strong>
												<em>
													<span id="total-item-pedido">
														<%=NumberUtil.formatarMoeda(totalPedido, true) %>
													</span>
												</em>
											</td>
										</tr>
									</tfoot>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<p>
				<button type="submit">Salvar</button>
				<button type="button" onclick="irPara('lista-scriptlet.jsp');">Voltar</button>
			</p>
		</form>
	</body>
	<script src="/ecommerce-web/assets/js/common.js"></script>
	<script src="/ecommerce-web/assets/js/pedido/editar-pedido.js"></script>
</html>
