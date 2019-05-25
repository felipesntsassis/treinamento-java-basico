package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet(name = "produto", urlPatterns = { "/admin/produto" })
public class AdicionaProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoDao dao = new ProdutoDao();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRequest = request.getParameter("id");

		if (idRequest == null || idRequest == "") {
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		}

		dao.remover(Long.valueOf(idRequest));

		feedback("removido", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Produto produto = new Produto();
		String idRequest = req.getParameter("id");
		String acao = "incluído";

		if (idRequest != null && idRequest != "") {
			produto.setId(Long.valueOf(idRequest));
		}

		produto.setDescricao(req.getParameter("descricao"));
		produto.setCategoria(new Categoria(Long.valueOf(req.getParameter("categoria"))));
		produto.setQuantidade(Integer.valueOf(req.getParameter("quantidade")));
		Double preco = Double.valueOf(req.getParameter("preco"));
		produto.setPreco(preco);

		if (produto.getId() != null && produto.getId() > 0) {
			dao.alterar(produto);
			acao = "alterado";
		} else {
			dao.adicionar(produto);
		}

		feedback(acao, resp);
	}

	private void feedback(String acao, HttpServletResponse resp) throws IOException {
		resp.addHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		StringBuilder resposta = new StringBuilder();
		resposta
			.append("<html>")
			.append("	<body>")
			.append("		<h3>Produto %s com sucesso!</h3>")
			.append("		<a href=\"/ecommerce-web/admin/produto/editar-taglib.jsp\">Novo Produto</a>")
			.append("		&nbsp;")
			.append("		<a href=\"/ecommerce-web/admin/produto/lista-taglib.jsp\">Voltar</a>")
			.append("	</body>")
			.append("</html>");
		out.println(String.format(resposta.toString(), acao));
	}

}
