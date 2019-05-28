package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.ProdutoDao;
import br.com.escolpi.ecommerce.modelo.Categoria;
import br.com.escolpi.ecommerce.modelo.Produto;
import br.com.escolpi.ecommerce.util.NumberUtil;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idRequest = req.getParameter("id");

		if (idRequest == null || idRequest == "") {
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		}

		dao.remover(Long.valueOf(idRequest));

		feedback("removido", req, resp);
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
		produto.setPreco(NumberUtil.parseDouble(req.getParameter("preco")));

		if (produto.getId() != null && produto.getId() > 0) {
			dao.alterar(produto);
			acao = "alterado";
		} else {
			dao.adicionar(produto);
		}

		feedback(acao, req, resp);
	}

	private void feedback(String acao, HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/produto/feedback.jsp");
		req.setAttribute("acao", acao);
		dispatcher.forward(req, resp);
	}

}
