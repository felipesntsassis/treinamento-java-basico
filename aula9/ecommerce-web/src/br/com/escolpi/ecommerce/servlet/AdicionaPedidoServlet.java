package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.SituacaoPedido;
import br.com.escolpi.ecommerce.jdbc.dao.ClienteDao;
import br.com.escolpi.ecommerce.jdbc.dao.ItemPedidoDao;
import br.com.escolpi.ecommerce.jdbc.dao.PedidoDao;
import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.ItemPedido;
import br.com.escolpi.ecommerce.modelo.Pedido;
import br.com.escolpi.ecommerce.modelo.Produto;

/**
 * Servlet implementation class LancarVenda
 */
@WebServlet("/admin/pedido")
public class AdicionaPedidoServlet extends HttpServlet {

	private static final long serialVersionUID = 6997851558729635418L;

	private ClienteDao clienteDao;
	private ItemPedidoDao itemPedidoDao;
	private PedidoDao pedidoDao;
	private VendedorDao vendedorDao;

	@Override
	public void init() throws ServletException {
		super.init();
		clienteDao = new ClienteDao();
		itemPedidoDao = new ItemPedidoDao();
		pedidoDao = new PedidoDao();
		vendedorDao = new VendedorDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");

		Long pedidoId = new Long(req.getParameter("id"));
		Pedido pedido = pedidoDao.obter(pedidoId);

		if (pedido == null)
			throw new IllegalArgumentException("Pedido não encontrado!");
		pedido.setSituacao(SituacaoPedido.CANCELADO);

		pedidoDao.alterar(pedido);
		feedback("cancelado", resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteDao.obter(Long.valueOf(req.getParameter("clienteId"))));
		pedido.setSituacao(SituacaoPedido.NOVO);
		pedido.setVendedor(vendedorDao.obter(Long.valueOf(req.getParameter("vendedorId"))));

		if (req.getParameter("id") != null && req.getParameter("id") != "") {
			pedido = pedidoDao.obter(Long.valueOf(req.getParameter("id")));
		}

		pedido.setItensPedido(new ArrayList<>());
		String acao = "incluído";

		if (pedido.getId() != null && pedido.getId() > 0) {
			//pedido.setSituacao(SituacaoPedido.obter(new Integer(req.getParameter("situacao"))));
			pedidoDao.alterar(pedido);
			acao = "alterado";
		} else {
			pedido.setDataPedido(Calendar.getInstance());
			pedidoDao.adicionar(pedido);
		}

		manterItensDoPedido(req, pedido);
		feedback(acao, resp);
	}

	private void manterItensDoPedido(HttpServletRequest req, Pedido pedido) {
		List<String> ids = Arrays.asList(req.getParameterValues("itemPedido.id"));
		List<String> produtoIds = Arrays.asList(req.getParameterValues("itemPedido.produtoId"));
		List<String> quantidades = Arrays.asList(req.getParameterValues("itemPedido.quantidade"));
		List<String> valores = Arrays.asList(req.getParameterValues("itemPedido.valor"));

		for (int i = 0; i < produtoIds.size(); i++) {
			ItemPedido itemPedido = new ItemPedido();

			if (ids.get(i) != "")
				itemPedido.setId(Long.valueOf(ids.get(i)));

			itemPedido.setPedido(pedido);
			itemPedido.setProduto(new Produto(Long.valueOf(produtoIds.get(i))));
			itemPedido.setQuantidade(Integer.valueOf(quantidades.get(i)));
			itemPedido.setValor(Double.valueOf(valores.get(i)));
			pedido.getItensPedido().add(itemPedido);
		}

		pedido.getItensPedido().stream()
			.filter(item -> item.getId() == null)
			.forEach(item -> itemPedidoDao.adicionar(item));
		pedido.getItensPedido().stream()
			.filter(item -> item.getId() != null)
			.forEach(item -> itemPedidoDao.alterar(item));

		if (req.getParameterValues("itensExclusao") != null) {
			List<String> itensExclusao = Arrays.asList(req.getParameterValues("itensExclusao"));
			itensExclusao.forEach(item -> itemPedidoDao.remover(Long.valueOf(item)));
		}
	}

	private void feedback(String acao, HttpServletResponse resp) throws IOException {
		resp.addHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		StringBuilder resposta = new StringBuilder()
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Pedido %s com sucesso!</h3>")
			.append("		<a href=\"/ecommerce-web/admin/pedido/editar-scriptlet.jsp\">Novo Pedido</a>")
			.append("		&nbsp;")
			.append("		<a href=\"/ecommerce-web/admin/pedido/lista-scriptlet.jsp\">Voltar</a>")
			.append("	</body>")
			.append("</html>");

		out.println(String.format(resposta.toString(), acao));
	}

}
