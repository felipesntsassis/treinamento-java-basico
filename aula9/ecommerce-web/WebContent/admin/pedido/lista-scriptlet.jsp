<%@ page import="br.com.escolpi.ecommerce.modelo.ItemPedido" %>
<%@ page import="br.com.escolpi.ecommerce.jdbc.dao.ItemPedidoDao" %>
<%@ page import="br.com.escolpi.ecommerce.jdbc.dao.PedidoDao" %>
<%@ page import="br.com.escolpi.ecommerce.modelo.Pedido" %>
<%@ page import="br.com.escolpi.ecommerce.util.DateUtil" %>
<%@ page import="br.com.escolpi.ecommerce.util.NumberUtil" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>E-Commerce: Lançamento de Pedidos</title>
		<link href="/ecommerce-web/assets/css/ecommerce.css" rel="stylesheet">
	</head>
	<body>
		<h3>Cadastro de Pedidos</h3>
		<nav>
			<ul>
				<li><a href="editar-scriptlet.jsp">Novo Pedido</a></li>
				<li><a href="/ecommerce-web/">Voltar para Home</a></li>
			</ul>
		</nav>
		<table border="1">
			<thead>
				<tr>
					<th>Data do Pedido</th>
					<th>Detalhe do Pedido</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
					PedidoDao dao = new PedidoDao();
					Calendar cal = Calendar.getInstance();
					List<Pedido> pedidos = dao.listar();

					if (pedidos != null && pedidos.size() > 0) {
						for (Pedido pedido : pedidos) {
				 %>
						<tr>
							<td><%=DateUtil.parseToString(pedido.getDataPedido(), "dd/MM/yyyy") %></td>
							<td>
								<table border="1">
									<tbody>
										<tr>
											<td>
												<strong>Vendedor(a) Responsável:</strong> 
												<%=pedido.getVendedor().getNome() %>
											</td>
										</tr>
										<tr>
											<td>
												<strong>Cliente:</strong> 
												<%=pedido.getCliente().getNome() %>
											</td>
										</tr>
										<tr>
											<td>
												<strong>Situação:</strong> 
												<%=pedido.getSituacao().getDescricao() %>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table border="1" class="w-100">
													<thead>
														<tr>
															<th colspan="4">Itens do Pedido</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<th>Item</th>
															<th>Preço</th>
															<th>Quantidade</th>
															<th>Valor do Item</th>
														</tr>
														<%
															ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
															pedido.setItensPedido(itemPedidoDao.listarPorPedido(pedido.getId()));
															if (pedido.getItensPedido() != null && pedido.getItensPedido().size() > 0) {
																for(ItemPedido item : pedido.getItensPedido()) {
														 %>
																	<tr>
																		<td><%=item.getProduto().getDescricao() %></td>
																		<td><%=NumberUtil.formatarMoeda(item.getProduto().getPreco(), true) %></td>
																		<td><%=item.getQuantidade() %></td>
																		<td><%=NumberUtil.formatarMoeda(item.getValor(), true) %></td>
																	</tr>
														<%
																}
															} else {
														 %>
															<tr>
																<td colspan="4" align="center">
																	<em>Este pedido não possui ítens.</em>
																</td>
															</tr>
														<%
															}
														 %>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td>
								<button type="button" onclick="">
									Aprovar
								</button>
								<br>
								<button type="button" onclick="">
									Cancelar
								</button>
								<br>
								<button type="button" onclick="irPara('/ecommerce-web/admin/pedido/editar-scriptlet.jsp?id=<%=pedido.getId() %>')">
									Editar
								</button>
								<br>
								<button type="button" onclick="confirmaExclusao(<%=pedido.getId() %>)">
									Excluir
								</button>
							</td>
						</tr>
				<%
						}
					} else {
				 %>
					<tr>
						<td colspan="3" align="center">
							<em>Nenhum Pedido Cadastrado</em>
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
			if (confirm('Deseja cancelar esta Pedido?')) {
				irPara('/ecommerce-web/admin/pedido?id=' + id);
			}
		};
	</script>
</html>
